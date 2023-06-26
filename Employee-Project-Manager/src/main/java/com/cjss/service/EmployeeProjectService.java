package com.cjss.service;

import com.cjss.entity.Employees;
import com.cjss.entity.Leaves;
import com.cjss.entity.Projects;
import com.cjss.exceptions.DataNotFoundException;
import com.cjss.exceptions.EmployeeNotFoundException;
import com.cjss.exceptions.PermissionDeniedToAccessException;
import com.cjss.exceptions.ProjectNotFoundException;
import com.cjss.model.*;
import com.cjss.repository.EmployeeRepo;
import com.cjss.repository.LeavesRepo;
import com.cjss.repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeProjectService {
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private LeavesRepo leavesRepo;

    //Adding Employee
    public String addEmployee(EmployeeModel employeeModel) {
        Employees employee = new Employees();
        employee.setEmpName(employeeModel.getEmpName());
        employee.setRole(employeeModel.getRole());
        employee.setAttendance(new LinkedHashMap<>());
        employee.setProjects(new HashSet<>());
        employee.setLeaves(new ArrayList<>());
        employeeRepo.save(employee);
        return "added";
    }

    // Adding Project
    public String addProject(ProjectModel projectModel) {
        Projects project = new Projects();
        project.setProjectName(projectModel.getProjectName());
        project.setDescription(projectModel.getDescription());
        project.setProjectStatus(projectModel.getProjectStatus());
        project.setEmployees(new HashSet<>());
        projectRepo.save(project);
        return "added";
    }

    // Assigning Project to Employee by id
    public String assignProjectToEmp(Long empId, Long projectId) throws ProjectNotFoundException {
        Optional<Employees> optionalEmployee = employeeRepo.findById(empId);
        Optional<Projects> optionalProject = projectRepo.findById(projectId);
        if (optionalProject.isPresent() && optionalEmployee.isPresent()){
            Employees employee = optionalEmployee.get();
            Projects project = optionalProject.get();
            project.getEmployees().add(employee);
            employee.getProjects().add(project);
            employeeRepo.save(employee);
            return "Assigned";
        }
        throw new ProjectNotFoundException("Project Not Found Please Enter Valid Project Id and try again");
    }

    // Adding Attendance By Employee id
    public String addAttendance(Long empId, Attendance attendance) throws EmployeeNotFoundException {
        Optional<Employees> optionalEmployee = employeeRepo.findById(empId);
        if (optionalEmployee.isPresent()){
            Employees employee = optionalEmployee.get();
            employee.getAttendance().putAll(attendance.getAttendance());
            employeeRepo.save(employee);
            return "Attendance Added";
        }
        throw new EmployeeNotFoundException("Employee Not Found Please Enter valid Employee Id");
    }

    // employee applying for leave by his id
    public String applyLeave(Long empId, LeavesModel leavesModel) throws EmployeeNotFoundException {
        Optional<Employees> optionalEmployee = employeeRepo.findById(empId);
        if (optionalEmployee.isPresent()){
            Employees employee = optionalEmployee.get();
            Leaves leaves = new Leaves();
            leaves.setStatus("pending");
            leaves.setEmployee(employee);
            leaves.setDate(leavesModel.getDate());
            employee.getLeaves().add(leaves);
            employeeRepo.save(employee);
            return "Requested";
        }
        throw new EmployeeNotFoundException("Employee Not Found Please Enter valid Employee Id");
    }

    // approving leaves of employees by admin
    public String leaveStatus(Long empId, Long projectId) throws EmployeeNotFoundException, ProjectNotFoundException, PermissionDeniedToAccessException{
        Scanner sc = new Scanner(System.in);
        Optional<Employees> optionalEmployee = employeeRepo.findById(empId);
        if(optionalEmployee.isPresent()){
            Employees employee = optionalEmployee.get();
            Optional<List<Employees>> projectsList = employeeRepo.findByProjectsProjectId(projectId);
            if (projectsList.isPresent()){
                if (employee.getRole().equalsIgnoreCase("admin") && projectsList.get().size() > 0){
                    List<Leaves> newList = leavesRepo.findByEmployeeProjectsProjectIdAndStatus(projectId, "pending");
                    for (Leaves leaves : newList){
                        System.out.println(leaves.getEmployee().getEmpId());
                        System.out.println(leaves.getEmployee().getEmpName());
                        System.out.println(leaves.getDate());
                        System.out.println(leaves.getStatus());
                        System.out.print("To approve leave enter Y and to cancel Leave Enter N: ");
                        String status = sc.next();
                        while (!(status.equalsIgnoreCase("y")|| status.equalsIgnoreCase("n"))){
                            System.out.println("Enter only Y to Approve, N to cancel");
                            status = sc.next();
                        }
                        if (status.equalsIgnoreCase("y"))
                            leaves.setStatus("approved");
                        else leaves.setStatus("canceled");
                        leavesRepo.save(leaves);
                    }
                    return "Changed";
                } else {
                    throw new PermissionDeniedToAccessException("Don't have a permission to change leave status");
                }
            } else {
                throw new ProjectNotFoundException("Project Not Found Please Enter Valid Project Id and try again");
            }
        } else {
            throw new EmployeeNotFoundException("Employee Not Found Please Enter valid Employee Id");
        }
    }

    // Fetching All Projects
    public List<Projects> getAllProjects() {
        return projectRepo.findAll();
    }

    //Fetching All Employees
    public List<Employees> getAllEmployees(){
        return employeeRepo.findAll();
    }

    // Fetching Employee based on project
    public List<EmployeeModel> getEmployeesByProject(Long projectId) throws ProjectNotFoundException{
        Optional<List<Employees>> optionalEmployees = employeeRepo.findByProjectsProjectId(projectId);
        if (optionalEmployees.isPresent()){
            List<Employees> employeesList = optionalEmployees.get();
            List<EmployeeModel> employeeModelList = employeesList.stream().map(employees -> {
                EmployeeModel model = new EmployeeModel();
                model.setEmpId(employees.getEmpId());
                model.setEmpName(employees.getEmpName());
                model.setRole(employees.getRole());
                return model;
            }).collect(Collectors.toList());
            return employeeModelList;
        }
        throw new ProjectNotFoundException("Project Not Found Please Enter Valid Project Id and try again");
    }

    // Fetching Employees leaves Date Range in current project
    public Map<String, Object> getEmployeesByLeavesDateOnCurrentProject(DateRangeModel dateRangeModel) throws DataNotFoundException {
        Optional<List<Employees>> optionalEmployees = employeeRepo.findByProjectsProjectStatusAndLeavesDateBetween(true,
                dateRangeModel.getStartDate(), dateRangeModel.getEndDate());
        if (optionalEmployees.isPresent()){
            List<Employees> employeesList = optionalEmployees.get();
            Map<String, Object> employeeMap = new LinkedHashMap<>();
            for (Employees employee: employeesList) {
                employeeMap.put("Employee Id", employee.getEmpId());
                employeeMap.put("Employee Name", employee.getEmpName());
                employeeMap.put("Employee Role", employee.getRole());
                List<LocalDate> leaves = new ArrayList<>();
                employee.getLeaves().stream()
                        .filter(leave -> leave.getDate().isAfter(dateRangeModel.getStartDate())
                                && leave.getDate().isBefore(dateRangeModel.getEndDate()))
                        .forEach(leave -> leaves.add(leave.getDate()));
                employeeMap.put("Leaves Date", leaves);
            }
            return employeeMap;
        } else {
            throw new DataNotFoundException("No employee is in leave between " + dateRangeModel.getStartDate() + " to " + dateRangeModel.getEndDate());
        }
    }

    // Fetching Project details by projects worked/ongoing by employee
    public Set<ProjectModel> getProjectDetailsOfEmployeeByStatus(Long empId, Boolean status) throws DataNotFoundException{
        Optional<Employees> optionalEmployee = employeeRepo.findByEmpIdAndProjectsProjectStatus(empId, status);
        if (optionalEmployee.isPresent()){
            Set<Projects> projectsSet = optionalEmployee.get().getProjects();
            Set<ProjectModel> projectModelSet = projectsSet.stream().map(projects ->{
                ProjectModel model = new ProjectModel();
                model.setProjectId(projects.getProjectId());
                model.setProjectName(projects.getProjectName());
                model.setProjectStatus(projects.getProjectStatus());
                model.setDescription(projects.getDescription());
                return model;
            }).collect(Collectors.toSet());
            return projectModelSet;
        } else
            throw new DataNotFoundException("No Data Found....");
    }
}
