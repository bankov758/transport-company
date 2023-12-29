package org.example;

import org.example.configuration.HibernateConfig;
import org.example.dao.*;
import org.example.util.MockDataGenerator;
import org.hibernate.Session;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()){
            MockDataGenerator dataGenerator = new MockDataGenerator();
            dataGenerator.generate();

            //Dependency injection :D
            CompanyDao companyDao = new CompanyDao();
            EmployeeDao employeeDao = new EmployeeDao();
            VehicleDao vehicleDao = new VehicleDao();
            ClientDao clientDao = new ClientDao();
            PayloadQualificationDao payloadQualificationDao = new PayloadQualificationDao();
            PayloadDao payloadDao = new PayloadDao();
            OrderDao orderDao = new OrderDao();
            ReceiptDao receiptDao = new ReceiptDao();

//            System.out.println(companyDao.getCompanyIncomeForPeriod("DHL",
//                    LocalDateTime.of(2023, 12, 24, 23, 0),
//                    LocalDateTime.of(2023, 12, 29, 23, 0)));
            System.out.println(orderDao.getById(1));
        }
    }

}