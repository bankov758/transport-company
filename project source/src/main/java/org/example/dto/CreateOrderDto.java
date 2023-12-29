package org.example.dto;

import org.example.entity.*;

import java.time.LocalDateTime;
import java.util.Set;

public class CreateOrderDto {

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String departurePoint;

    private String arrivalPoint;

    private float price;

    private Payload payload;

    private Employee driver;

    private Company company;

    private Vehicle vehicle;

    private Set<Client> clients;

    public CreateOrderDto(LocalDateTime startTime, LocalDateTime endTime,
                          String departurePoint, String arrivalPoint,
                          float price, Payload payload,
                          Employee driver, Company company,
                          Vehicle vehicle, Set<Client> clients) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.departurePoint = departurePoint;
        this.arrivalPoint = arrivalPoint;
        this.price = price;
        this.payload = payload;
        this.driver = driver;
        this.company = company;
        this.vehicle = vehicle;
        this.clients = clients;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public String getArrivalPoint() {
        return arrivalPoint;
    }

    public float getPrice() {
        return price;
    }

    public Payload getPayload() {
        return payload;
    }

    public Employee getDriver() {
        return driver;
    }

    public Company getCompany() {
        return company;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Set<Client> getClients() {
        return clients;
    }
}
