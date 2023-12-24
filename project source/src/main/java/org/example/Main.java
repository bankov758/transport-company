package org.example;

import org.example.configuration.HibernateConfig;
import org.example.dao.CompanyDao;
import org.example.dao.EmployeeDao;
import org.example.entity.Company;
import org.example.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Session session = HibernateConfig.getSessionFactory().openSession();
        try (session){
            Company company = new Company();
            company.setName("DHL");

            CompanyDao companyDao = new CompanyDao(Company.class);
            companyDao.create(company);
            //companyDao.delete(company);
            System.out.println(companyDao.getAll());
            //company.setName("aaaa");
            //companyDao.update(company);
            //System.out.println(companyDao.getById(1));
        }
    }
}