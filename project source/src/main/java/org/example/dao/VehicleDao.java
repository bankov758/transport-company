package org.example.dao;

import org.example.entity.Vehicle;

public class VehicleDao extends AbstractDao<Vehicle>{

    public VehicleDao(Class<Vehicle> clazz) {
        super(clazz);
    }
}
