package org.example;

import org.example.configuration.HibernateConfig;
import org.example.dao.*;
import org.example.dto.*;
import org.example.entity.*;
import org.example.service.EmployeeService;
import org.example.util.*;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

            createCompanies(companyDao);
            createVehicles(vehicleDao, companyDao);
            createPayloads(payloadQualificationDao, payloadDao);
            createPersons(employeeDao, companyDao, clientDao, payloadQualificationDao);
            createOrders(payloadDao, employeeDao, companyDao, clientDao, vehicleDao, orderDao);

            //System.out.println(payloadQualificationDao.getAll());
            System.out.println(employeeDao.getAll());
        }
    }

    private static void createOrders(PayloadDao payloadDao, EmployeeDao employeeDao, CompanyDao companyDao, ClientDao clientDao, VehicleDao vehicleDao, OrderDao orderDao) {
        Order order = OrderMapper.dtoToObject(new CreateOrderDto(
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(2),
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

    private static void createPersons(EmployeeDao employeeDao, CompanyDao companyDao, ClientDao clientDao,PayloadQualificationDao payloadQualificationDao) {
        Employee employee = PersonMapper.createDtoToObject(new CreateEmployeeDto(
                "Ivo",
                "Bankov",
                "0141164465",
                companyDao.getByField("name", "DHL")
        ));
        employeeDao.create(employee);
        EmployeeService employeeService = new EmployeeService();
        employeeService.addPayloadQualification(employee,"Flammable goods");

        clientDao.create(PersonMapper.createDtoToObject(new CreateClientDto(
                "Petar",
                "Petkov",
                "1141164465")));
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