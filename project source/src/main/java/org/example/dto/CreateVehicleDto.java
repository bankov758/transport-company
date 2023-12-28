package org.example.dto;

import org.example.entity.Company;
import org.example.entity.enumeration.CapacityUnit;

public class CreateVehicleDto {

    private String type;

    private CapacityUnit capacityUnit;

    private float capacity;

    private String registrationNumber;

    private Company company;

    public CreateVehicleDto(String type, CapacityUnit capacityUnit, float capacity, String registrationNumber, Company company) {
        this.type = type;
        this.capacityUnit = capacityUnit;
        this.capacity = capacity;
        this.registrationNumber = registrationNumber;
        this.company = company;
    }

    public String getType() {
        return type;
    }

    public CapacityUnit getCapacityUnit() {
        return capacityUnit;
    }

    public float getCapacity() {
        return capacity;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Company getCompany() {
        return company;
    }
}
