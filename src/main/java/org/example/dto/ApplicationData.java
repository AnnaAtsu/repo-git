package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)

public class ApplicationData {

    private Integer applicationid;
    private Integer citizenid;
    private Integer applicantid;
    private Integer staffid;
    @JsonProperty("dateofapplication")
    private String dateOfApplication;
    private String kindofapplication;
    private String statusofapplicaion;
    private String channel;
    private String image;


}
