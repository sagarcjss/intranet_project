package com.cjss.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class LeavesModel {
    @DateTimeFormat(pattern = "yy-MM-dd")
    private LocalDate date;

    @DateTimeFormat(pattern = "yy-MM-dd")
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "LeavesModel{" +
                "date=" + date +
                '}';
    }
}
