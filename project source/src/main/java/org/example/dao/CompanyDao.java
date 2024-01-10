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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CompanyDao extends AbstractDao<Company> {

    public CompanyDao() {
        super(Company.class);
    }

    /**
     * Retrieves a list of employees from a specified company.
     *
     * @param companyName The name of the company whose employees are requested.
     * @return A list of employees from a specified company.
     */
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

    /**
     * Retrieves a list of vehicles of a specified company.
     *
     * @param companyName The name of the company for which vehicle information is requested.
     * @return A List of vehicles.
     */
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

    /**
     * Filters companies by companyName and provides an optional sorting order.
     *
     * @param companyName  The companyName to filter companies by.
     * @param sort  Optional sorting order for the result. If not provided, the result is not sorted.
     * @return A list of companies.
     */
    public List<Company> filterByName(String companyName, Optional<OrderBy> sort) {
        List<Company> companies;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            StringBuilder queryString = new StringBuilder("select c from Company c" +
                    " where c.name like concat('%', :companyName, '%') ");
            sort.ifPresent(value -> {
                queryString.append(" order by name ").append(value);
            });
            companies = session.createQuery(queryString.toString(), Company.class)
                    .setParameter("companyName", companyName)
                    .getResultList();
            transaction.commit();
        }
        return companies;
    }

    /**
     * Filters companies by income and provides an optional sorting order.
     *
     * @param income               The income to filter companies by.
     * @param comparisonOperator   The comparison operator for filtering.
     * @param sort                 Optional sorting order for the result. If not provided, the result is not sorted.
     * @return A List of CompanyDtos.
     */
    public List<CompanyDto> filterByIncome(Float income, QueryOperator comparisonOperator, Optional<OrderBy> sort) {
        List<CompanyDto> companies;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            StringBuilder queryString = new StringBuilder(" SELECT company.name AS name, SUM(coalesce(price, 0)) AS income " +
                    " FROM company " +
                    " left join transport_company.transport_order on company.id = transport_order.company_id " +
                    " left join transport_company.receipt on receipt.order_id = transport_order.id " +
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

    /**
     * Retrieves the total income of a company for a specified period.
     *
     * @param companyName  The name of the company.
     * @param periodStart   The start of the period for calculating income.
     * @param periodEnd     The end of the period for calculating income.
     * @return The total income of the specified company for the given period.
     */
    public Double getCompanyIncomeForPeriod(String companyName, LocalDateTime periodStart, LocalDateTime periodEnd) {
        Double income;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            StringBuilder queryString = new StringBuilder(" SELECT sum(transport_order.price) FROM transport_company.company " +
                    " inner join transport_company.transport_order on company.id = transport_order.company_id " +
                    " inner join transport_company.receipt on receipt.order_id = transport_order.id " +
                    " where company.name = :companyName " +
                    " and transport_order.start_time >= :periodStart " +
                    " and transport_order.end_time <= :periodEnd ");
            income = session.createNativeQuery(queryString.toString(), Double.class)
                    .setParameter("companyName", companyName)
                    .setParameter("periodStart", periodStart)
                    .setParameter("periodEnd", periodEnd)
                    .getSingleResult();
            transaction.commit();
        }
        return income;
    }

}
