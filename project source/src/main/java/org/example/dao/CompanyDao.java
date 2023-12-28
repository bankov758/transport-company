package org.example.dao;


import org.example.configuration.HibernateConfig;
import org.example.dto.CompanyDto;
import org.example.dto.EmployeeDto;
import org.example.dto.VehicleDto;
import org.example.entity.Company;
import org.example.entity.enumeration.OrderBy;
import org.example.entity.enumeration.QueryOperator;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class CompanyDao extends AbstractDao<Company> {

    private static final String UNSUPPORTED_SORT =
            "Sort should have at least one or maximum two params divided by _ symbol!";


    public CompanyDao() {
        super(Company.class);
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
                            " select new org.example.dto.VehicleDto(v.id, v.type, v.registrationNumber, c.name) from Vehicle v " +
                                    " join v.company c " +
                                    " where c.name = :companyName ",
                            VehicleDto.class)
                    .setParameter("companyName", companyName)
                    .getResultList();
            transaction.commit();
        }
        return vehicles;
    }

    public List<Company> filterByName(String name, boolean isDesc) {
        List<Company> companies;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String orderBy = isDesc ? " desc " : " asc ";
            companies = session.createQuery(
                            "select c from Company c" +
                                    " where c.name = :name " +
                                    " order by name " + orderBy,
                            Company.class)
                    .setParameter("name", name)
                    .getResultList();
            transaction.commit();
        }
        return companies;
    }

    public List<CompanyDto> filterByIncome(Float income, QueryOperator comparisonOperator, Optional<OrderBy> sort) {
        List<CompanyDto> companies;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            StringBuilder queryString = new StringBuilder(" SELECT company.name AS name, SUM(price) AS income " +
                    " FROM company " +
                    " inner join transport_company.transport_order on company.id = transport_order.company_id " +
                    " inner join transport_company.receipt on receipt.order_id = order_id " +
                    " GROUP BY company.name ");
            queryString.append(" having income ").append(comparisonOperator.getSymbol()).append(income);
            sort.ifPresent(value -> {
                queryString.append(" order by income ").append(value);
            });
            companies = session.createNativeQuery(queryString.toString(),
                    CompanyDto.RESULT_SET_MAPPING_NAME, CompanyDto.class).getResultList();
            transaction.commit();
        }
        return companies;
    }

}
