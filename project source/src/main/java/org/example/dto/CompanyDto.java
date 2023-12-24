package org.example.dto;

public class CompanyDto {
    public String name;

    public CompanyDto() {
    }

    public CompanyDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
