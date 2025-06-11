package org.example.generator;

import java.time.LocalDate;

public class AssingLeaveVO {

    private  final String employeeName, comments;
    private final String fromDate, toDate;


    public AssingLeaveVO(String employeeName, String fromDate, String toDate, String comments) {
        this.employeeName = employeeName;
        this.comments = comments;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

        public String getEmployeeName() { return employeeName; }
        public String getComments() { return comments; }
        public String getFromDate() { return fromDate; }
        public String getToDate() { return toDate; }

}
