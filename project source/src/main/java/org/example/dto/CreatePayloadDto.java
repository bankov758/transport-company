package org.example.dto;

import org.example.entity.CapacityUnit;
import org.example.entity.PayloadQualification;

public class CreatePayloadDto {

    private CapacityUnit unit;

    private float unitValue;

    private PayloadQualification payloadQualification;

    public CreatePayloadDto(CapacityUnit unit, float unitValue, PayloadQualification payloadQualification) {
        this.unit = unit;
        this.unitValue = unitValue;
        this.payloadQualification = payloadQualification;
    }

    public CapacityUnit getUnit() {
        return unit;
    }

    public float getUnitValue() {
        return unitValue;
    }

    public PayloadQualification getPayloadQualification() {
        return payloadQualification;
    }
}
