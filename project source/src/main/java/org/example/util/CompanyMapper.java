package org.example.util;

import org.example.dto.CompanyDto;
import org.example.entity.Company;

public class CompanyMapper {
    public static Company dtoToObject(CompanyDto companyDto){
        Company company = new Company();
        company.setName(companyDto.getName());
        return company;
    }
}
