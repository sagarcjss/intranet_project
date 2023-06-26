package com.cjss.model;

import java.time.LocalDate;
import java.util.Map;

public class Attendance {
    Map<LocalDate, Boolean> attendance;

    public Map<LocalDate, Boolean> getAttendance() {
        return attendance;
    }

    public void setAttendance(Map<LocalDate, Boolean> attendance) {
        this.attendance = attendance;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "attendance=" + attendance +
                '}';
    }
}
