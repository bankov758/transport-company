package org.example;

import org.example.configuration.HibernateConfig;
import org.example.dao.*;
import org.example.dto.CreatePayloadDto;
import org.example.dto.CreatePayloadQualificationDto;
import org.example.entity.*;
import org.example.util.PayloadMapper;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        try (session){
            CompanyDao companyDao = new CompanyDao(Company.class);
            EmployeeDao employeeDao = new EmployeeDao(Employee.class);
            VehicleDao vehicleDao = new VehicleDao(Vehicle.class);
            ClientDao clientDao = new ClientDao(Client.class);
            PayloadQualificationDao payloadQualificationDao = new PayloadQualificationDao(PayloadQualification.class);
            PayloadDao payloadDao = new PayloadDao(Payload.class);


            //companyDao.create(CompanyMapper.dtoToObject(new CompanyDto("DHL")));

//            vehicleDao.create(VehicleMapper.createDtoToObject(
//                    new CreateVehicleDto("truck", CapacityUnit.KILOGRAM,
//                            1000, "ov0112da",
//                            companyDao.getByField("name", "DHL"))));
//
//            vehicleDao.create(VehicleMapper.createDtoToObject(
//                    new CreateVehicleDto("semi-truck", CapacityUnit.KILOGRAM,
//                            500, "ov0113da",
//                            companyDao.getByField("name", "DHL"))));

//            System.out.println(companyDao.getCompanyVehiclesDTO("DHL"));

//            employeeDao.create(PersonMapper.createDtoToObject(new CreateEmployeeDto(
//                    "Ivo",
//                    "bankov",
//                    "0141164465",
//                    companyDao.getByField("name", "DHL")
//            )));

//            clientDao.create(PersonMapper.createDtoToObject(new CreateClientDto(
//                    "pesho",
//                    "bankov",
//                    "1141164465")));

            payloadQualificationDao.create(PayloadMapper.dtoToObject(new CreatePayloadQualificationDto("Flammable goods")));
            payloadDao.create(PayloadMapper.dtoToObject(new CreatePayloadDto(
                    CapacityUnit.KILOGRAM,
                    100,
                    payloadQualificationDao.getByField("qualification", "Flammable goods"))));

            System.out.println(payloadQualificationDao.getAll());
            System.out.println(payloadDao.getAll());
        }
    }
}