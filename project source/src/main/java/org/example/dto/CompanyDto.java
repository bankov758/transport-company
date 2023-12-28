package org.example.dto;

public class CompanyDto {

    public static final String RESULT_SET_MAPPING_NAME = "CompanyDTOMapping";

    public String name;

    public double income;

    public CompanyDto(String name) {
        this.name = name;
    }

    public CompanyDto(String name, double income) {
        this(name);
        this.income = income;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "CompanyDto{" +
                "name='" + name + '\'' +
                ", income=" + income +
                '}';
    }
}
