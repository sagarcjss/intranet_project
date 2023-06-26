package com.cjss.repository;

import com.cjss.entity.Leaves;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeavesRepo extends JpaRepository<Leaves, Long> {
    public List<Leaves> findByEmployeeProjectsProjectIdAndStatus(Long projectId, String status);
}
