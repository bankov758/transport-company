package org.example.service;

import org.example.dao.EmployeeDao;
import org.example.dao.PayloadQualificationDao;
import org.example.entity.Employee;
import org.example.entity.PayloadQualification;

import java.util.HashSet;
import java.util.Set;

public class EmployeeService {

    private final PayloadQualificationDao payloadQualificationDao;
    private final EmployeeDao employeeDao;

    public EmployeeService() {
        employeeDao = new EmployeeDao();
        payloadQualificationDao = new PayloadQualificationDao();
    }

    /**
     * Adds a payload qualification to an employees.
     *
     * @param employee      The employee to which the qualification will be added.
     * @param qualification The qualification to be added to the employee's payload qualifications.
     */
    public void addPayloadQualification(Employee employee, String qualification){
        PayloadQualification payloadQualification =
                payloadQualificationDao.getByField("qualification", qualification);
        Set<PayloadQualification> qualifications =
                employee.getPayloadQualifications() == null ? new HashSet<>() : employee.getPayloadQualifications();
        qualifications.add(payloadQualification);
        employee.setPayloadQualifications(qualifications);
        employeeDao.update(employee);
    }

}
