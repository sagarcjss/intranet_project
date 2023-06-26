package com.cjss.model;

import java.time.LocalDate;

public class DateRangeModel {
    private LocalDate startDate;
    private LocalDate endDate;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "DateRangeModel{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
