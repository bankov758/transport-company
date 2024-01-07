package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.example.entity.enumeration.CapacityUnit;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "vehicle",
        uniqueConstraints = @UniqueConstraint(columnNames = {"registration_number"}))
public class Vehicle {

    private static final int REG_NUM_LENGTH = 8;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    private String type;

    @Enumerated(EnumType.STRING)
    @Column(name = "capacity_unit")
    private CapacityUnit capacityUnit;

    @Column(name = "capacity")
    private float capacity;

    @Column(name = "registration_number")
    @Pattern(regexp = "^[A-Za-z]{2}\\d{4}[A-Za-z]{2}$", message = "Vehicle's registration number does not match the pattern!")
    private String registrationNumber;

    @ManyToOne(optional = false)
    private Company company;

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY)
    private Set<Order> orders;

    public Vehicle() {
    }

    public Vehicle(int id, String type, CapacityUnit capacityUnit,
                   float capacity, String registrationNumber, Company company) {
        this.id = id;
        this.type = type;
        this.capacityUnit = capacityUnit;
        this.capacity = capacity;
        this.registrationNumber = registrationNumber;
        this.company = company;
    }

    public Vehicle(int id, String type, CapacityUnit capacityUnit, float capacity,
                   Company company, String registrationNumber, Set<Order> orders) {
        this(id, type, capacityUnit, capacity, registrationNumber, company);
        this.orders = orders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CapacityUnit getCapacityUnit() {
        return capacityUnit;
    }

    public void setCapacityUnit(CapacityUnit capacityUnit) {
        this.capacityUnit = capacityUnit;
    }

    public float getCapacity() {
        return capacity;
    }

    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle vehicle)) return false;
        return Float.compare(capacity, vehicle.capacity) == 0
                && Objects.equals(type, vehicle.type)
                && capacityUnit == vehicle.capacityUnit
                && Objects.equals(registrationNumber, vehicle.registrationNumber)
                && Objects.equals(company, vehicle.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, capacityUnit, capacity, registrationNumber, company);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", capacityUnit=" + capacityUnit +
                ", capacity=" + capacity +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", company=" + company +
                '}';
    }
}
