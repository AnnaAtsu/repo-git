package org.example.generator;

public class JobVO {

    private final String jobTitle, jobDescription, jobNote;

    public JobVO(String jobTitle, String jobDescription, String jobNote) {
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobNote = jobNote;

    }


    public String getJobTitle() {
        return  jobTitle;
    }
    public  String getJobDescription() {
        return  jobDescription;
    }

    public String getJobNote() {
        return jobNote;
    }
}
