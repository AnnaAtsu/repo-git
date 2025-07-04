package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProcessRequest (    @JsonProperty
    Integer applId,
    @JsonProperty
    Long staffid,
    @JsonProperty
    String action)
{
}
