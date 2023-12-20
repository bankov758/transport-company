package org.example.dao;

import org.example.entity.Employee;

public class EmployeeDao extends AbstractDao<Employee> {

    public EmployeeDao(Class<Employee> clazz) {
        super(clazz);
    }

}
