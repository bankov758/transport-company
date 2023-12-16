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
            //Company company = new Company(1, "Sasdasdas", new HashSet<>());
            //Employee employee = new Employee(1, "ivo", "bankov", company);

            //CompanyDao.createCompany(company);
            //EmployeeDao.createEmployee(employee);

            //System.out.println(EmployeeDao.getEmployeeById(1));
        }
    }
}