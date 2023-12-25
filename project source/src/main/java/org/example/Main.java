package org.example;

import org.example.configuration.HibernateConfig;
import org.example.dao.ClientDao;
import org.example.dao.CompanyDao;
import org.example.dao.EmployeeDao;
import org.example.dao.VehicleDao;
import org.example.dto.CreateClientDto;
import org.example.dto.CreateEmployeeDto;
import org.example.entity.Client;
import org.example.entity.Company;
import org.example.entity.Employee;
import org.example.entity.Vehicle;
import org.example.util.PersonMapper;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        try (session){
            CompanyDao companyDao = new CompanyDao(Company.class);
            EmployeeDao employeeDao = new EmployeeDao(Employee.class);
            VehicleDao vehicleDao = new VehicleDao(Vehicle.class);
            ClientDao clientDao = new ClientDao(Client.class);


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

            employeeDao.create(PersonMapper.createDtoToObject(new CreateEmployeeDto(
                    "Ivo",
                    "bankov",
                    "0141164465",
                    companyDao.getByField("name", "DHL")
            )));

//            clientDao.create(PersonMapper.createDtoToObject(new CreateClientDto(
//                    "pesho",
//                    "bankov",
//                    "1141164465")));
            System.out.println(employeeDao.getAll());
        }
    }
}