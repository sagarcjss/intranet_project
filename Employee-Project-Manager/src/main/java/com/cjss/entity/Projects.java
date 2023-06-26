package com.cjss.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projectId")
    @SequenceGenerator(initialValue = 100001, sequenceName = "prj_", name = "projectId")
    private Long projectId;

    private String projectName;

    private String description;

    private Boolean projectStatus;

    @JsonIgnore
    @ManyToMany(mappedBy = "projects")
    private Set<Employees> employees;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Employees> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employees> employees) {
        this.employees = employees;
    }

    public Boolean getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Boolean projectStatus) {
        this.projectStatus = projectStatus;
    }

    @Override
    public String toString() {
        return "Projects{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", description='" + description + '\'' +
                ", employees=" + employees +
                ", projectStatus=" + projectStatus +
                '}';
    }
}
