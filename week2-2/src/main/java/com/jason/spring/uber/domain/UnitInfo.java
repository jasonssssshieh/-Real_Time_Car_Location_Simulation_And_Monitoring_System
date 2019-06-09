package com.jason.spring.uber.domain;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@Embeddable
@RequiredArgsConstructor
public class UnitInfo {

    private final String unitVin;//他在初始化后,值没法再改变, vin会跟着对象 是immutable的
    private String engineMake;
    private String customerName;
    private String unitNumber;

    @SuppressWarnings("unused")
    public UnitInfo(){
        this.unitVin = "";
    }
}
