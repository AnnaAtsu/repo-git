package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public record UserRequest ( @JsonProperty String mode,
                           @JsonProperty String personalLastName,
                            @JsonProperty String personalFirstName,
                            @JsonProperty String personalMiddleName,
                            @JsonProperty String personalPhoneNumber,
                            @JsonProperty String personalNumberOfPassport,
                            @JsonProperty String personalAddress,
                            @JsonProperty String citizenLastName,
                            @JsonProperty String citizenFirstName,
                            @JsonProperty String citizenMiddleName,
                            @JsonProperty String citizenBirthDate,
                            @JsonProperty String citizenNumberOfPassport,
                            @JsonProperty String citizenGender,
                            @JsonProperty  String citizenAddress,
                            @JsonProperty  String dateOfMarriage,
                            @JsonProperty  String newLastName,
                            @JsonProperty  String anotherPersonLastName,
                            @JsonProperty  String anotherPersonFirstName,
                            @JsonProperty  String anotherPersonMiddleName,
                            @JsonProperty  String birth_of_anotoherPerson,
                            @JsonProperty  String anotherPersonPassport,
                            @JsonProperty  Long  birth_place,
                            @JsonProperty  Long  birth_mother,
                            @JsonProperty  Long  birth_father,
                            @JsonProperty  Long birth_grandpa,
                            @JsonProperty  Long  birth_grandma,
                            @JsonProperty  Long death_dateOfDeath,
                            @JsonProperty  Long death_placeOfDeath
)

{

}
