package com.cjss.model;

import com.cjss.annotations.BooleanCheck;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ProjectModel {

    private Long projectId;
    @NotBlank
    @Size(min = 3, max = 30, message = "Product name length should in between 3 to 30")
    private String projectName;

    @NotBlank(message = "description should not be empty...")
    private String description;

    @BooleanCheck
    private Boolean projectStatus;

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

    public Boolean getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Boolean projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "ProjectModel{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", description='" + description + '\'' +
                ", projectStatus=" + projectStatus +
                '}';
    }
}
