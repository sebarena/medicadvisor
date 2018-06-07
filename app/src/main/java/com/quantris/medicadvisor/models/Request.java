package com.quantris.medicadvisor.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Request {

    @JsonCreator
    public Request() {

    }

    @JsonProperty("name")
    public String Name;
}
