package org.example.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import org.example.dao.EmployeeDao;
import org.example.dao.OrderDao;
import org.example.dao.PayloadQualificationDao;
import org.example.entity.Client;
import org.example.entity.Employee;
import org.example.entity.Order;
import org.example.entity.PayloadQualification;

import java.time.format.DateTimeFormatter;
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
