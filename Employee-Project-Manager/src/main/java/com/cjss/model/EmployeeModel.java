package com.cjss.model;

import com.cjss.annotations.EmpRoleCheck;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EmployeeModel {

    private Long empId;
    @NotBlank
    @Size(min = 3, max = 30, message = "name length should in between 3 to 30")
    private String empName;
    @EmpRoleCheck
    private String role;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    @Override
    public String toString() {
        return "EmployeeModel{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
