package com.quantris.medicadvisor.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomDate {

    @JsonCreator
    public CustomDate() {
        this.$date = new Date();
    }

    @JsonProperty("$date")
    public Date $date;
}
