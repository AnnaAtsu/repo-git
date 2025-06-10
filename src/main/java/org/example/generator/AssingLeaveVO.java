package org.example.generator;

import java.time.LocalDate;

public class AssingLeaveVO {

    private  final String employeeName, comments;
    private final LocalDate fromDate;
    private LocalDate toDate;


    public AssingLeaveVO(String employeeName, LocalDate fromDate, LocalDate toDate, String comments) {
        this.employeeName = employeeName;
        this.comments = comments;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

        public String getEmployeeName() { return employeeName; }
        public String getComments() { return comments; }
        public LocalDate getFromDate() { return fromDate; }
        public LocalDate getToDate() { return toDate; }

}
