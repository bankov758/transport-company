package org.example.util;

import org.example.dao.*;
import org.example.dto.*;
import org.example.entity.Employee;
import org.example.entity.Order;
import org.example.entity.enumeration.CapacityUnit;
import org.example.service.EmployeeService;
import org.example.service.OrderService;
import org.example.util.mappers.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

public class MockDataGenerator {

    private final CompanyDao companyDao;
    private final EmployeeDao employeeDao;
    private final VehicleDao vehicleDao;
    private final ClientDao clientDao;
    private final PayloadQualificationDao payloadQualificationDao;
    private final PayloadDao payloadDao;
    private final OrderDao orderDao;
    private final ReceiptDao receiptDao;

    public MockDataGenerator() {
        companyDao = new CompanyDao();
        employeeDao = new EmployeeDao();
        vehicleDao = new VehicleDao();
        clientDao = new ClientDao();
        payloadQualificationDao = new PayloadQualificationDao();
        payloadDao = new PayloadDao();
        orderDao = new OrderDao();
        receiptDao = new ReceiptDao();
    }

    public void generate(){
        createCompanies();
        createVehicles();
        createPayloads();
        createEmployees();
        createClients();
        createOrders();

        OrderService orderService = new OrderService();
        orderService.pay(1, 2);
        orderService.pay(1, 3);
    }

    private void createOrders() {
        Order order = OrderMapper.dtoToObject(new CreateOrderDto(
                LocalDateTime.of(2023, 12, 24, 23, 0),
                LocalDateTime.of(2023, 12, 29, 23, 0),
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

    private void createPayloads() {
        payloadQualificationDao.create(PayloadMapper.dtoToObject(new CreatePayloadQualificationDto("Flammable goods")));
        payloadDao.create(PayloadMapper.dtoToObject(new CreatePayloadDto(
                CapacityUnit.KILOGRAM,
                100,
                payloadQualificationDao.getByField("qualification", "Flammable goods"))));
    }

    private void createCompanies() {
        companyDao.create(CompanyMapper.dtoToObject(new CompanyDto("DHL")));
        companyDao.create(CompanyMapper.dtoToObject(new CompanyDto("Speedy")));
        companyDao.create(CompanyMapper.dtoToObject(new CompanyDto("Discordia")));
    }

    private void createEmployees() {
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
    }

    private void createClients() {
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

    private void createVehicles() {
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
