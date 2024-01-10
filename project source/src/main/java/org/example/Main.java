package org.example;

import org.example.dao.*;
import org.example.entity.enumeration.OrderBy;
import org.example.entity.enumeration.QueryOperator;
import org.example.service.OrderService;
import org.example.util.MockDataGenerator;
import org.example.util.Printer;

import java.time.LocalDateTime;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        //Generate mock data
        MockDataGenerator dataGenerator = new MockDataGenerator();
        //dataGenerator.generate();

        //Dependency injection :D
        CompanyDao companyDao = new CompanyDao();
        EmployeeDao employeeDao = new EmployeeDao();
        ClientDao clientDao = new ClientDao();
        OrderDao orderDao = new OrderDao();
        OrderService orderService = new OrderService();

        //7a
        Printer.printEntities(companyDao.filterByIncome(1000f, QueryOperator.MORE_OR_EQUALS, Optional.of(OrderBy.DESC)));
        Printer.printEntities(companyDao.filterByName("Econt", Optional.of(OrderBy.DESC)));

        //7b
        Printer.printEntities(employeeDao.getEmployeesByQualification("Double payload", Optional.of(OrderBy.DESC)));
        Printer.printEntities(employeeDao.getEmployeesBySalary(2000f, QueryOperator.LESS_OR_EQUALS, Optional.of(OrderBy.ASC)));

        //7c
        Printer.printOrders(orderDao.filterByDestination("Atina"));

        //8
        Printer.printOrders(orderDao.getAll());

        //9
        Printer.printEntity("Number of finished Orders for DHL - " + orderDao.getNumberOfFinishedOrdersForCompany("DHL"));
        Printer.printEntity("Income from finished Orders for DHL - " + orderDao.getIncomeFromOrdersForCompany("DHL"));
        Printer.printEntities("Employees with number of orders; ", employeeDao.getEmployeesWithNumOfOrders(), null);

        LocalDateTime startTime = LocalDateTime.of(2023, 12, 24, 23, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 5, 3, 15, 0);
        Printer.printEntity("Income for DHL between " + startTime + " and " + endTime,
                companyDao.getCompanyIncomeForPeriod("DHL",
                        startTime, endTime), null);

        Printer.printEntities("Employees and their income", employeeDao.getEmployeesWithPayedOrders(), null);

        //some additional
        Printer.printEntities(companyDao.getCompanyEmployeesDTO("DHL"));
        Printer.printEntities(companyDao.getCompanyVehiclesDTO("DHL"));
        Printer.printEntities(clientDao.getAll("firstName", OrderBy.ASC));
        Printer.printEntity("Is order with id 1 payed - ", orderService.isOrderPayed(1), null);
        orderService.pay(1, "Hristo");
        Printer.printEntity("Is order with id 1 payed - ", orderService.isOrderPayed(1), null);

    }
}