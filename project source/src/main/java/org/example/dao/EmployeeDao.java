package org.example.dao;

import org.example.configuration.HibernateConfig;
import org.example.entity.Employee;
import org.example.exception.DuplicateEntityException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployeeDao extends AbstractDao<Employee> {

    public EmployeeDao(Class<Employee> clazz) {
        super(clazz);
    }

    @Override
    public void create(Employee entity) {
        checkIfEmployeeExists(entity);
        super.create(entity);
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
                    .getSingleResult();
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
