package org.example.utils;

import org.example.dto.CreateEmployeeDto;
import org.example.entity.Employee;

public class EmployeeMapper {
    public static Employee createDtoToObject(CreateEmployeeDto employeeDto){
        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setSsn(employeeDto.getSsn());
        employee.setCompany(employeeDto.getCompany());
        return employee;
    }
}
