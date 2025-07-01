package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)

public class ApplicationData {
    private Integer applicationId;
    private Integer citizenId;
    private Integer applicantId;

    private Integer staffId;
    @JsonProperty("dateofapplication")
    private String dateOfApplication;
    private String kindofApplication;
    private String statusofapplicaion;
    private String channel;
    private String image;


}
