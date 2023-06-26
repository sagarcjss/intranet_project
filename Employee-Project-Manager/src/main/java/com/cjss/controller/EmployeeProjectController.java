package com.cjss.controller;

import com.cjss.entity.Employees;
import com.cjss.entity.Leaves;
import com.cjss.entity.Projects;
import com.cjss.model.*;
import com.cjss.service.EmployeeProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class EmployeeProjectController {

    @Autowired
    private EmployeeProjectService employeeProjectService;

    //Adding Employee
    @PostMapping("save-emp")
    public ResponseEntity<String> addEmployee(@RequestBody @Valid EmployeeModel employee){
        String status = employeeProjectService.addEmployee(employee);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    // Adding Project
    @PostMapping("add-project")
    public ResponseEntity<String> addProject(@RequestBody @Valid ProjectModel project){
        String status = employeeProjectService.addProject(project);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    // Assigning Project to Employee by id
    @PutMapping("add-project-emp/{empId}/{projectId}")
    public ResponseEntity<String> assignProjectToEmp(@PathVariable Long empId, @PathVariable Long projectId){
        String status = employeeProjectService.assignProjectToEmp(empId, projectId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    // Adding Attendance By Employee id
    @PutMapping("add-attendance/{empId}")
    public ResponseEntity<String> addAttendance(@PathVariable Long empId, @RequestBody Attendance attendance){
        String status = employeeProjectService.addAttendance(empId, attendance);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    // employee applying for leave by his id
    @PutMapping("apply-leave/{empId}")
    public ResponseEntity<String> applyLeave(@PathVariable Long empId, @RequestBody LeavesModel leaves){
        return new ResponseEntity<>(employeeProjectService.applyLeave(empId, leaves), HttpStatus.OK);
    }

    // approving leaves of employees by admin
    @PutMapping("leaves-status/{empId}/{projectId}")
    public ResponseEntity<String> leavesStatus(@PathVariable Long empId, @PathVariable Long projectId){
        return new ResponseEntity<>(employeeProjectService.leaveStatus(empId, projectId), HttpStatus.OK);
    }

    // Fetching All Projects
    @GetMapping("get-all-projects")
    public ResponseEntity<List<Projects>> getAllProjects(){
        return new ResponseEntity<>(employeeProjectService.getAllProjects(), HttpStatus.OK);
    }

    //Fetching All Employees
    @GetMapping("get-all-employees")
    public ResponseEntity<List<Employees>> getAllEmployees(){
        return new ResponseEntity<>(employeeProjectService.getAllEmployees(), HttpStatus.OK);
    }

    // Fetching Employee based on project
    @GetMapping("get-all-employee-project/{projectId}")
    public ResponseEntity<List<EmployeeModel>> getEmployeesByProject(@PathVariable Long projectId){
        return new ResponseEntity<>(employeeProjectService.getEmployeesByProject(projectId),HttpStatus.OK);
    }

    // Fetching Employees leaves Date Range in current project
    @GetMapping("get-employees-leave-by-date-range")
    public ResponseEntity<Map<String, Object>> getEmployeesByLeavesDateRangeInCurrentProject(@RequestBody DateRangeModel dateRangeModel){
        return new ResponseEntity<>(employeeProjectService.getEmployeesByLeavesDateOnCurrentProject(dateRangeModel), HttpStatus.OK);
    }

    // Fetching Project details by projects worked/ongoing by employee
    @GetMapping("get-project-by-project-status-employee/{empId}/{status}")
    public ResponseEntity<Set<ProjectModel>> getProjectDetailsOfEmployeeByStatus(@PathVariable Long empId, @PathVariable Boolean status){
        return new ResponseEntity<>(employeeProjectService.getProjectDetailsOfEmployeeByStatus(empId, status), HttpStatus.OK);
    }
}
