package org.example.dto;

import org.example.entity.Company;

public class CreateEmployeeDto extends CreatePersonDto {

    private Company company;

    public CreateEmployeeDto(String firstName, String lastName, String ssn, Company company) {
        super(firstName, lastName, ssn);
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
