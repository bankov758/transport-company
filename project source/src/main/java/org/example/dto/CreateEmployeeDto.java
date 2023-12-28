package org.example.dto;

import org.example.entity.Company;

public class CreateEmployeeDto extends CreatePersonDto {

    private float salary;

    private Company company;

    public CreateEmployeeDto(String firstName, String lastName, String ssn, float salary, Company company) {
        super(firstName, lastName, ssn);
        this.salary = salary;
        this.company = company;
    }

    public float getSalary() {
        return salary;
    }

    public Company getCompany() {
        return company;
    }

}
