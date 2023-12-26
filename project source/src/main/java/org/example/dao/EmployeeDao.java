package org.example.dao;

import org.example.configuration.HibernateConfig;
import org.example.entity.Employee;
import org.example.entity.Payload;
import org.example.exception.DuplicateEntityException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        Employee employee;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employee = session.createQuery(
                            " select e from Employee e " +
                                    " join e.payloadQualifications pQ " +
                                    " where pQ.qualification = :qualification ",
                            Employee.class)
                    .setParameter("qualification", qualification)
                    .getSingleResult();
            transaction.commit();
        }
        return employee;
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
