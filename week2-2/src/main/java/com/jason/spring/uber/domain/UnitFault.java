package com.jason.spring.uber.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@Embeddable
@RequiredArgsConstructor
public class UnitFault {
    private final String vin;
    private Long spn;
    private Long fmi;

    @SuppressWarnings("unused")
    public UnitFault(){
        this.vin = "";
    }

}
