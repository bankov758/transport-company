package org.example;

import org.example.configuration.HibernateConfig;
import org.example.dao.CompanyDao;
import org.example.entity.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Session session = HibernateConfig.getSessionFactory().openSession();
        try (session){
            Company company = new Company(4, "Sasdasdas");

            CompanyDao.createCompany(company);
            System.out.println(CompanyDao.getCompaniesByName("SAP", false));

            //CompanyDao.getCompanies().stream().forEach(System.out::println);
        }
    }
}