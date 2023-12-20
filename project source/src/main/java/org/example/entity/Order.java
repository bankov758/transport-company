package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "transport_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "departure_point")
    private String departurePoint;

    @Column(name = "arrival_point")
    private String arrivalPoint;

    @Column(name = "price")
    private float price;

    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY)
    private Payload payload;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee driver;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    private Vehicle vehicle;

    @ManyToMany(mappedBy = "orders", fetch = FetchType.LAZY)
    private Set<Client> clients;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private Set<Receipt> receipts;

    public Order() {
    }

    public Order(long id, LocalDateTime startTime, LocalDateTime endTime, String departurePoint, String arrivalPoint,
                 float price, Payload payload, Employee driver, Company company, Vehicle vehicle, Set<Client> clients) {
        this.id = id;
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

    public Order(long id, LocalDateTime startTime, LocalDateTime endTime, String departurePoint, String arrivalPoint,
                 float price, Payload payload, Employee driver, Company company, Vehicle vehicle, Set<Client> clients, Set<Receipt> receipts) {
        this(id, startTime, endTime, departurePoint, arrivalPoint, price, payload, driver, company, vehicle, clients);
        this.receipts = receipts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public String getArrivalPoint() {
        return arrivalPoint;
    }

    public void setArrivalPoint(String arrivalPoint) {
        this.arrivalPoint = arrivalPoint;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public Employee getDriver() {
        return driver;
    }

    public void setDriver(Employee driver) {
        this.driver = driver;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Set<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(Set<Receipt> receipts) {
        this.receipts = receipts;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id
                && Float.compare(price, order.price) == 0
                && Objects.equals(startTime, order.startTime)
                && Objects.equals(endTime, order.endTime)
                && Objects.equals(departurePoint, order.departurePoint)
                && Objects.equals(arrivalPoint, order.arrivalPoint)
                && Objects.equals(payload, order.payload)
                && Objects.equals(driver, order.driver)
                && Objects.equals(company, order.company)
                && Objects.equals(vehicle, order.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, endTime, departurePoint, arrivalPoint, price, payload, driver, company, vehicle);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", departurePoint='" + departurePoint + '\'' +
                ", arrivalPoint='" + arrivalPoint + '\'' +
                ", price=" + price +
                ", payload=" + payload +
                ", driver=" + driver +
                ", company=" + company +
                '}';
    }
}
