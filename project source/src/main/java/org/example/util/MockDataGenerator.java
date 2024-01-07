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

    /**
     * Generates mock data also covers all requirements from 1 to 6 (without deletion)
     */
    public void generate() {
        createCompanies();
        createVehicles();
        createPayloads();
        createEmployees();
        createClients();
        createOrders();

        OrderService orderService = new OrderService();
        orderService.pay(1, "Silvia");
        orderService.pay(3, "Konstantin");
    }

    private void createOrders() {
        OrderService orderService = new OrderService();
        Order order = OrderMapper.dtoToObject(new CreateOrderDto(
                LocalDateTime.of(2023, 12, 24, 23, 0),
                LocalDateTime.of(2023, 12, 29, 23, 0),
                "Sofia",
                "Atina",
                100,
                payloadDao.getPayloadByQualification("Bus"),
                employeeDao.getEmployeeByQualification("Bus"),
                companyDao.getByField("name", "DHL"),
                vehicleDao.getById(3),
                new HashSet<>(Arrays.asList(clientDao.getByField("ssn", "1141164465")))
        ));
        orderDao.create(order);
        orderService.addClient(order, "Silvia");
        orderService.addClient(order, "Hristo");
        Order order1 = OrderMapper.dtoToObject(new CreateOrderDto(
                LocalDateTime.of(2024, 1, 14, 10, 0),
                LocalDateTime.of(2024, 1, 19, 23, 0),
                "London",
                "Amsterdam",
                3000,
                payloadDao.getPayloadByQualification("Double payload"),
                employeeDao.getEmployeeByQualification("Double payload"),
                companyDao.getByField("name", "Speedy"),
                vehicleDao.getById(2),
                new HashSet<>(Arrays.asList(clientDao.getByField("ssn", "1141164465")))
        ));
        orderDao.create(order1);
        orderService.addClient(order1, "Konstantin");
        Order order2 = OrderMapper.dtoToObject(new CreateOrderDto(
                LocalDateTime.of(2024, 5, 2, 11, 0),
                LocalDateTime.of(2024, 5, 3, 15, 0),
                "Burgas",
                "Pleven",
                1000,
                payloadDao.getPayloadByQualification("Flammable goods"),
                employeeDao.getEmployeeByQualification("Flammable goods"),
                companyDao.getByField("name", "DHL"),
                vehicleDao.getById(1),
                new HashSet<>(Arrays.asList(clientDao.getByField("ssn", "1141164465")))
        ));
        orderDao.create(order2);
        orderService.addClient(order2, "Marian");
    }

    private void createPayloads() {
        payloadQualificationDao.create(PayloadMapper.dtoToObject(new CreatePayloadQualificationDto("Flammable goods")));
        payloadDao.create(PayloadMapper.dtoToObject(new CreatePayloadDto(
                CapacityUnit.KILOGRAM,
                700,
                payloadQualificationDao.getByField("qualification", "Flammable goods"))));
        payloadQualificationDao.create(PayloadMapper.dtoToObject(new CreatePayloadQualificationDto("Bus")));
        payloadDao.create(PayloadMapper.dtoToObject(new CreatePayloadDto(
                CapacityUnit.PERSON,
                50,
                payloadQualificationDao.getByField("qualification", "Bus"))));
        payloadQualificationDao.create(PayloadMapper.dtoToObject(new CreatePayloadQualificationDto("Double payload")));
        payloadDao.create(PayloadMapper.dtoToObject(new CreatePayloadDto(
                CapacityUnit.POUND,
                5000,
                payloadQualificationDao.getByField("qualification", "Double payload"))));
        payloadQualificationDao.create(PayloadMapper.dtoToObject(new CreatePayloadQualificationDto("Oil tanker")));
        payloadDao.create(PayloadMapper.dtoToObject(new CreatePayloadDto(
                CapacityUnit.LITER,
                2000,
                payloadQualificationDao.getByField("qualification", "Oil tanker"))));
    }

    private void createCompanies() {
        companyDao.create(CompanyMapper.dtoToObject(new CompanyDto("DHL")));
        companyDao.create(CompanyMapper.dtoToObject(new CompanyDto("Speedy")));
        companyDao.create(CompanyMapper.dtoToObject(new CompanyDto("Discordia")));
        companyDao.create(CompanyMapper.dtoToObject(new CompanyDto("Econt")));
    }

    private void createEmployees() {
        Employee employee = PersonMapper.createDtoToObject(new CreateEmployeeDto(
                "Ivo",
                "Bankov",
                "0141864465",
                2000,
                companyDao.getByField("name", "Speedy")
        ));
        employeeDao.create(employee);
        EmployeeService employeeService = new EmployeeService();
        employeeService.addPayloadQualification(employee, "Flammable goods");
        Employee employee2 = PersonMapper.createDtoToObject(new CreateEmployeeDto(
                "Martina",
                "Georgieva",
                "0141964465",
                2000,
                companyDao.getByField("name", "DHL")
        ));
        employeeDao.create(employee2);
        employeeService.addPayloadQualification(employee2, "Bus");
        Employee employee3 = PersonMapper.createDtoToObject(new CreateEmployeeDto(
                "Kristina",
                "Marinova",
                "0140164465",
                1000,
                companyDao.getByField("name", "DHL")
        ));
        employeeDao.create(employee3);
        employeeService.addPayloadQualification(employee3, "Oil tanker");

        Employee employee4 = PersonMapper.createDtoToObject(new CreateEmployeeDto(
                "Ivailo",
                "Dimitrov",
                "0141164435",
                3000,
                companyDao.getByField("name", "Speedy")
        ));
        employeeDao.create(employee4);
        employeeService.addPayloadQualification(employee4, "Double payload");

        Employee employee5 = PersonMapper.createDtoToObject(new CreateEmployeeDto(
                "Petya",
                "Ivanova",
                "0141164439",
                3500,
                companyDao.getByField("name", "Speedy")
        ));
        employeeDao.create(employee5);
        employeeService.addPayloadQualification(employee5, "Double payload");
    }

    private void createClients() {
        clientDao.create(PersonMapper.createDtoToObject(new CreateClientDto(
                "Silvia",
                "Petkova",
                "1141164465")));
        clientDao.create(PersonMapper.createDtoToObject(new CreateClientDto(
                "Ivan",
                "Ivanov",
                "1141164455")));
        clientDao.create(PersonMapper.createDtoToObject(new CreateClientDto(
                "Borislava",
                "Boyanova",
                "1141164444")));
        clientDao.create(PersonMapper.createDtoToObject(new CreateClientDto(
                "Marian",
                "Hristov",
                "1241164465")));
        clientDao.create(PersonMapper.createDtoToObject(new CreateClientDto(
                "Hristo",
                "Smirnenski",
                "1341164455")));
        clientDao.create(PersonMapper.createDtoToObject(new CreateClientDto(
                "Konstantin",
                "Pavlov",
                "1741164444")));
    }

    private void createVehicles() {
        vehicleDao.create(VehicleMapper.createDtoToObject(
                new CreateVehicleDto("truck", CapacityUnit.KILOGRAM,
                        1000, "ov0112da",
                        companyDao.getByField("name", "DHL"))));
        vehicleDao.create(VehicleMapper.createDtoToObject(
                new CreateVehicleDto("double-truck", CapacityUnit.POUND,
                        6000, "ov0113da",
                        companyDao.getByField("name", "Speedy"))));
        vehicleDao.create(VehicleMapper.createDtoToObject(
                new CreateVehicleDto("Bus", CapacityUnit.PERSON,
                        50, "ov0114da",
                        companyDao.getByField("name", "DHL"))));
        vehicleDao.create(VehicleMapper.createDtoToObject(
                new CreateVehicleDto("tanker-truck", CapacityUnit.LITER,
                        3000, "ov0115da",
                        companyDao.getByField("name", "Discordia"))));
    }

}
