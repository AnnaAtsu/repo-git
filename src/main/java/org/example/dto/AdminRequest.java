package org.example.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


public record AdminRequest(
        @JsonProperty String personalLastName,
        @JsonProperty
        String personalFirstName,
        @JsonProperty
         String personalMiddleName,
        @JsonProperty
         String personalPhoneNumber,
        @JsonProperty
        String personalNumberOfPassport,
        @JsonProperty
         String dateofbirth) {

}
