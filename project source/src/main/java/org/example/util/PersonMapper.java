package org.example.util;

import org.example.dto.CreateClientDto;
import org.example.dto.CreateEmployeeDto;
import org.example.dto.CreatePersonDto;
import org.example.entity.Client;
import org.example.entity.Employee;
import org.example.entity.Person;

public class PersonMapper {
    public static Employee createDtoToObject(CreateEmployeeDto employeeDto){
        Employee employee = createDtoToObject(employeeDto, new Employee());
        employee.setSalary(employeeDto.getSalary());
        employee.setCompany(employeeDto.getCompany());
        return employee;
    }

    public static Client createDtoToObject(CreateClientDto clientDto){
        return createDtoToObject(clientDto, new Client());
    }

    private static <T extends CreatePersonDto, K extends Person> K createDtoToObject(T personDto, K person){
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        person.setSsn(personDto.getSsn());
        return person;
    }

}
