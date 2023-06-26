package com.cjss.repository;

import com.cjss.entity.Employees;
import com.cjss.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employees, Long> {
    public Optional<List<Employees>> findByProjectsProjectId(Long projectId);
    public Optional<List<Employees>> findByProjectsProjectStatusAndLeavesDateBetween(Boolean status, LocalDate startDate, LocalDate endDate);
    public Optional<Employees> findByEmpIdAndProjectsProjectStatus(Long empId, Boolean status);
}
