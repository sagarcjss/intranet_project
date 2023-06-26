package com.cjss.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empId")
    @SequenceGenerator(initialValue = 101, sequenceName = "CJSS_", name = "empId")
    private Long empId;

    private String empName;

    private String role;

    @ElementCollection
    @CollectionTable(name = "attendance", joinColumns = {@JoinColumn(name = "empId", referencedColumnName = "empId")})
    @MapKeyColumn(name = "date")
    @Column(name = "status")
    private Map<LocalDate, Boolean> attendance;

    @ManyToMany
//    @JoinTable(name = "employee-project",
//    joinColumns = @JoinColumn(name = "empId"),
//            inverseJoinColumns = @JoinColumn(name = "projectId")
//    )
    private Set<Projects> projects;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
    private List<Leaves> leaves;

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

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

    public Map<LocalDate, Boolean> getAttendance() {
        return attendance;
    }

    public void setAttendance(Map<LocalDate, Boolean> attendance) {
        this.attendance = attendance;
    }

    public Set<Projects> getProjects() {
        return projects;
    }

    public void setProjects(Set<Projects> projects) {
        this.projects = projects;
    }

    public List<Leaves> getLeaves() {
        return leaves;
    }

    public void setLeaves(List<Leaves> leaves) {
        this.leaves = leaves;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", role='" + role + '\'' +
                ", attendance=" + attendance +
                ", projects=" + projects +
                ", leaves=" + leaves +
                '}';
    }
}
