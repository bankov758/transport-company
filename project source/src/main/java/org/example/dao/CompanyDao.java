package org.example.dao;


import org.example.configuration.HibernateConfig;
import org.example.dao.contracts.CrudDao;
import org.example.dto.EmployeeDto;
import org.example.dto.VehicleDto;
import org.example.entity.Company;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CompanyDao extends AbstractDao<Company> {

    public CompanyDao(Class<Company> clazz) {
        super(clazz);
    }

    public List<EmployeeDto> getCompanyEmployeesDTO(String companyName) {
        List<EmployeeDto> employees;
        try (Session session =  HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employees = session.createQuery(
                            "select new org.example.dto.EmployeeDto(e.id, e.firstName, e.lastName)" +
                                    " from Employee e" +
                                    " join e.company c " +
                                    "where c.name = :companyName",
                            EmployeeDto.class)
                    .setParameter("companyName", companyName)
                    .getResultList();
            transaction.commit();
        }
        return employees;
    }

    public List<VehicleDto> getCompanyVehiclesDTO(String companyName) {
        List<VehicleDto> vehicles;
        try (Session session =  HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicles = session.createQuery(
                            "select new org.example.dto.VehicleDto(v.id, v.type, v.registrationNumber, c.name) from Vehicle v" +
                                    " join v.company c " +
                                    "where c.name = :companyName",
                            VehicleDto.class)
                    .setParameter("companyName", companyName)
                    .getResultList();
            transaction.commit();
        }
        return vehicles;
    }

}
