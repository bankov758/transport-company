package org.example.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import org.example.dao.EmployeeDao;
import org.example.dao.OrderDao;
import org.example.dao.PayloadQualificationDao;
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


    public void printOrderToPdf(Order order, OrderDao orderDao) {
        String filePath = "E:\\Projects\\transport_company\\project source\\src\\main\\resources\\example.pdf";
        try (PdfWriter writer = new PdfWriter(filePath)) {
            try (PdfDocument pdf = new PdfDocument(writer)) {
                try (Document document = new Document(pdf)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    Paragraph title = new Paragraph("Order #" + order.getId())
                            .setFontSize(18)
                            .setBold()
                            .setTextAlignment(TextAlignment.CENTER);
                    document.add(title);
                    document.add(new Paragraph("Arrival point: " + order.getArrivalPoint()));
                    document.add(new Paragraph("Departure point: " + order.getDeparturePoint()));
                    document.add(new Paragraph("Start time: " + order.getStartTime().format(formatter)));
                    document.add(new Paragraph("End time: " + order.getEndTime().format(formatter)));
                    document.add(new Paragraph("Price: " + order.getPrice()));
                    document.add(new Paragraph("Driver: " + order.getDriver().getFirstName() + " " + order.getDriver().getLastName()));
                    document.add(new Paragraph("Company: " + order.getCompany().getName()));
//                    for (Client client : orderDao.) {
//                        document.add(new Paragraph("Clients: ").setFirstLineIndent(1));
//                        //document.add(new)
//                    }
                }
            }
            System.out.println("PDF created successfully at: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
