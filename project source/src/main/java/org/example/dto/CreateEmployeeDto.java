package org.example.dto;

import org.example.entity.Company;

public class CreateEmployeeDto {

    private String firstName;
    private String lastName;
    private String ssn;
    private Company company;

    public CreateEmployeeDto() {
    }

    public CreateEmployeeDto(String firstName, String lastName, String ssn, Company company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.company = company;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
