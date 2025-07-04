package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public record UserRequest ( @JsonProperty String mode,
                           @JsonProperty String  personalLastName,
                           @JsonProperty   String personalMiddleName,
                           @JsonProperty  String personalPhoneNumber,
                           @JsonProperty  String personalNumberOfPassport,
                           @JsonProperty   String citizenLastName,
                           @JsonProperty   String citizenFirstName,
                           @JsonProperty   String citizenMiddleName,
                           @JsonProperty   String citizenBirthDate,
                           @JsonProperty  String citizenNumberOfPassport,
                           @JsonProperty   String citizenGender,
                           @JsonProperty   String dateOfMarriage,
                           @JsonProperty   String newLastName,
                           @JsonProperty   String anotherPersonLastName,
                           @JsonProperty   String anotherPersonFirstName,
                           @JsonProperty   String anotherPersonMiddleName,
                           @JsonProperty  String birth_of_anotoherPerson,
                           @JsonProperty   String anotherPersonPassport,
                           @JsonProperty   String birth_place,
                           @JsonProperty   String birth_mother,
                           @JsonProperty   String birth_father,
                           @JsonProperty  String death_dateOfDeath,
                            @JsonProperty String death_placeOfDeath)

{

}
