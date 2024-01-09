package org.example;

import org.example.dao.*;
import org.example.entity.enumeration.OrderBy;
import org.example.entity.enumeration.QueryOperator;
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
        VehicleDao vehicleDao = new VehicleDao();
        ClientDao clientDao = new ClientDao();
        PayloadQualificationDao payloadQualificationDao = new PayloadQualificationDao();
        PayloadDao payloadDao = new PayloadDao();
        OrderDao orderDao = new OrderDao();
        ReceiptDao receiptDao = new ReceiptDao();

//        7a
        Printer.printEntities(companyDao.filterByIncome(1000f, QueryOperator.MORE_OR_EQUALS, Optional.of(OrderBy.DESC)));
        Printer.printEntities(companyDao.filterByName("Econt", Optional.of(OrderBy.DESC)));

//        7b
        Printer.printEntities(employeeDao.getEmployeesByQualification("Double payload", Optional.of(OrderBy.DESC)));
        Printer.printEntities(employeeDao.getEmployeesBySalary(2000f, QueryOperator.LESS_OR_EQUALS, Optional.of(OrderBy.ASC)));

//        7c
        Printer.printOrders(orderDao.filterByDestination("Atina"));

//        8
        Printer.printOrders(orderDao.getAll());

//        9
        Printer.printEntity("Number of finished Orders for DHL - " + orderDao.getNumberOfFinishedOrdersForCompany("DHL"));
        Printer.printEntity("Income from finished Orders for DHL - " + orderDao.getIncomeFromOrdersForCompany("DHL"));
        Printer.printEntities("Employees with number of orders; ", employeeDao.getEmployeesWithNumOfOrders(), null);

        LocalDateTime startTime = LocalDateTime.of(2023, 12, 24, 23, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 5, 3, 15, 0);
        Printer.printEntity("Income for DHL between " + startTime + " and " + endTime,
                companyDao.getCompanyIncomeForPeriod("DHL",
                        startTime, endTime), null);

        Printer.printEntities("Employees and their income", employeeDao.getEmployeesWithPayedOrders(), null);
    }

}