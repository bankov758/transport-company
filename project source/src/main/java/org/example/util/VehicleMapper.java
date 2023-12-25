package org.example.util;

import org.example.dto.CreateVehicleDto;
import org.example.entity.Vehicle;

public class VehicleMapper {
    public static Vehicle createDtoToObject(CreateVehicleDto vehicleDto){
        Vehicle vehicle = new Vehicle();
        vehicle.setType(vehicleDto.getType());
        vehicle.setCapacityUnit(vehicleDto.getCapacityUnit());
        vehicle.setCapacity(vehicleDto.getCapacity());
        vehicle.setRegistrationNumber(vehicleDto.getRegistrationNumber());
        vehicle.setCompany(vehicleDto.getCompany());
        return vehicle;
    }
}
