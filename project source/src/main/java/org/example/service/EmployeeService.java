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
