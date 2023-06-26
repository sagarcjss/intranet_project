package com.cjss.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Leaves {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "leaveId")
    @SequenceGenerator(initialValue = 900001, name = "leaveId")
    private Long leaveId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "empId")
    private Employees employee;

    private LocalDate date;

    private String status;

    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Leaves{" +
                "leaveId=" + leaveId +
                ", employee=" + employee +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}
