package org.example.dao;

import org.example.configuration.HibernateConfig;
import org.example.dto.EmployeeDto;
import org.example.entity.Employee;
import org.example.entity.enumeration.OrderBy;
import org.example.entity.enumeration.QueryOperator;
import org.example.exception.DuplicateEntityException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class EmployeeDao extends AbstractDao<Employee> {

    public EmployeeDao() {
        super(Employee.class);
    }

    @Override
    public void create(Employee entity) {
        checkIfEmployeeExists(entity);
        super.create(entity);
    }

    public Employee getEmployeeByQualification(String qualification) {
        List<Employee> employees = getEmployeesByQualification(qualification, Optional.empty());
        if (employees != null && !employees.isEmpty()) {
            return employees.get(0);
        }
        return null;
    }

    public List<Employee> getEmployeesByQualification(String qualification, Optional<OrderBy> orderBySalary) {
        List<Employee> employee;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            StringBuilder queryBuilder = new StringBuilder(" select e from Employee e " +
                    " join e.payloadQualifications pQ " +
                    " where pQ.qualification = :qualification ");
            orderBySalary.ifPresent(value -> {
                queryBuilder.append(" order by salary ").append(value);
            });
            employee = session.createQuery(queryBuilder.toString(), Employee.class)
                    .setParameter("qualification", qualification)
                    .getResultList();
            transaction.commit();
        }
        return employee;
    }

    public List<Employee> getEmployeesBySalary(float salary, QueryOperator queryOperator, Optional<OrderBy> orderBySalary) {
        List<Employee> employee;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            StringBuilder queryBuilder = new StringBuilder(" select e from Employee e " +
                    " where e.salary " + queryOperator.getSymbol() + " :salary ");
            orderBySalary.ifPresent(value -> {
                queryBuilder.append(" order by salary ").append(value);
            });
            employee = session.createQuery(queryBuilder.toString(), Employee.class)
                    .setParameter("salary", salary)
                    .getResultList();
            transaction.commit();
        }
        return employee;
    }

    public List<EmployeeDto> getEmployeesWithPayedOrders() {
        List<EmployeeDto> employees;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employees = session.createQuery(
                            " select new org.example.dto.EmployeeDto(e.id, e.firstName, e.lastName, " +
                                    " count(distinct(e.id)), sum(o.price)) " +
                                    " from Employee e " +
                                    " join e.orders o " +
                                    " join o.receipts r " +
                                    " group by e.id ",
                            EmployeeDto.class)
                    .getResultList();
            transaction.commit();
        }
        return employees;
    }

    public List<EmployeeDto> getEmployeesWithNumOfOrders() {
        List<EmployeeDto> employees;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employees = session.createQuery(
                            " select new org.example.dto.EmployeeDto(e.id, e.firstName, e.lastName, count(o.id)) " +
                                    " from Employee e " +
                                    " left join e.orders o " +
                                    " group by e.id ",
                            EmployeeDto.class)
                    .getResultList();
            transaction.commit();
        }
        return employees;
    }

    private void checkIfEmployeeExists(Employee entity) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employee = session.createQuery(
                            "select e from Employee e" +
                                    " where e.firstName = :firstName " +
                                    " and e.lastName = :lastName " +
                                    " and e.ssn = :ssn ",
                            Employee.class)
                    .setParameter("firstName", entity.getFirstName())
                    .setParameter("lastName", entity.getLastName())
                    .setParameter("ssn", entity.getSsn())
                    .getSingleResultOrNull();
            transaction.commit();
            if (employee != null) {
                throw new DuplicateEntityException(
                        Employee.class.getSimpleName(),
                        "name and ssn",
                        entity.getFirstName() + " " + entity.getLastName() + " " + entity.getSsn());
            }
        }
    }
}
