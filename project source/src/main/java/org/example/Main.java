package org.example;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import org.example.configuration.HibernateConfig;
import org.example.dao.*;
import org.example.dto.*;
import org.example.entity.*;
import org.example.entity.enumeration.CapacityUnit;
import org.example.entity.enumeration.OrderBy;
import org.example.service.EmployeeService;
import org.example.service.OrderService;
import org.example.util.*;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()){
            //Dependency injection :D
            CompanyDao companyDao = new CompanyDao();
            EmployeeDao employeeDao = new EmployeeDao();
            VehicleDao vehicleDao = new VehicleDao();
            ClientDao clientDao = new ClientDao();
            PayloadQualificationDao payloadQualificationDao = new PayloadQualificationDao();
            PayloadDao payloadDao = new PayloadDao();
            OrderDao orderDao = new OrderDao();
            ReceiptDao receiptDao = new ReceiptDao();

//            createCompanies(companyDao);
//            createVehicles(vehicleDao, companyDao);
//            createPayloads(payloadQualificationDao, payloadDao);
//            createPersons(employeeDao, companyDao, clientDao);
//            createOrders(payloadDao, employeeDao, companyDao, clientDao, vehicleDao, orderDao);
//
//            OrderService orderService = new OrderService();
//            orderService.pay(1, 2);
//            orderService.pay(1, 3);

            System.out.println(employeeDao.getEmployeesWithOrders());
            //System.out.println(orderService.isOrderPayed(1));

            //createPdf(orderDao.getByField("id", "1"), orderDao);
        }
    }

    public static void createPdf(Order order, OrderDao orderDao) {
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

    private static void createOrders(PayloadDao payloadDao, EmployeeDao employeeDao, CompanyDao companyDao, ClientDao clientDao, VehicleDao vehicleDao, OrderDao orderDao) {
        Order order = OrderMapper.dtoToObject(new CreateOrderDto(
                LocalDateTime.now().minusDays(3),
                LocalDateTime.now().minusDays(2),
                "Sofia",
                "Atina",
                1000,
                payloadDao.getPayloadByQualification("Flammable goods"),
                employeeDao.getEmployeeByQualification("Flammable goods"),
                companyDao.getByField("name", "DHL"),
                new HashSet<>(Arrays.asList(clientDao.getByField("ssn", "1141164465")))
        ));
        order.setVehicle(vehicleDao.getSuitableForOrder(order));
        orderDao.create(order);
        OrderService orderService = new OrderService();
        orderService.addClient(order, 2);
        orderService.addClient(order, 3);
        orderService.addClient(order, 4);
    }

    private static void createPayloads(PayloadQualificationDao payloadQualificationDao, PayloadDao payloadDao) {
        payloadQualificationDao.create(PayloadMapper.dtoToObject(new CreatePayloadQualificationDto("Flammable goods")));
        payloadDao.create(PayloadMapper.dtoToObject(new CreatePayloadDto(
                CapacityUnit.KILOGRAM,
                100,
                payloadQualificationDao.getByField("qualification", "Flammable goods"))));
    }

    private static void createCompanies(CompanyDao companyDao) {
        companyDao.create(CompanyMapper.dtoToObject(new CompanyDto("DHL")));
    }

    private static void createPersons(EmployeeDao employeeDao, CompanyDao companyDao, ClientDao clientDao) {
        //Employees
        Employee employee = PersonMapper.createDtoToObject(new CreateEmployeeDto(
                "Ivo",
                "Bankov",
                "0141164465",
                1000,
                companyDao.getByField("name", "DHL")
        ));
        employeeDao.create(employee);
        EmployeeService employeeService = new EmployeeService();
        employeeService.addPayloadQualification(employee,"Flammable goods");

        //Clients
        clientDao.create(PersonMapper.createDtoToObject(new CreateClientDto(
                "Petar",
                "Petkov",
                "1141164465")));
        clientDao.create(PersonMapper.createDtoToObject(new CreateClientDto(
                "ivan",
                "ivanov",
                "1141164455")));
        clientDao.create(PersonMapper.createDtoToObject(new CreateClientDto(
                "bobi",
                "boyanov",
                "1141164444")));
    }

    private static void createVehicles(VehicleDao vehicleDao, CompanyDao companyDao) {
        vehicleDao.create(VehicleMapper.createDtoToObject(
                new CreateVehicleDto("truck", CapacityUnit.KILOGRAM,
                        1000, "ov0112da",
                        companyDao.getByField("name", "DHL"))));
        vehicleDao.create(VehicleMapper.createDtoToObject(
                new CreateVehicleDto("semi-truck", CapacityUnit.KILOGRAM,
                        500, "ov0113da",
                        companyDao.getByField("name", "DHL"))));
    }

}