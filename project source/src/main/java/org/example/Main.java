package org.example;

import org.example.configuration.SessionFactoryUtil;
import org.example.dao.CompanyDao;
import org.example.entity.Company;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SessionFactoryUtil.getSessionFactory().openSession();

        Company company = new Company(4, "Sasdasdas");

        //CompanyDao.createCompany(company);
        System.out.println(CompanyDao.getCompaniesByName("SAP", false));

        //CompanyDao.getCompanies().stream().forEach(System.out::println);
    }
}