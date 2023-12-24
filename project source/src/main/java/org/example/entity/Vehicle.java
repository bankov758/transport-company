package org.example.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "vehicle")
public class Vehicle {

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY)
    private Set<Order> orders;

    public Vehicle() {
    }

    public Vehicle(int id, String type, CapacityUnit capacityUnit, float capacity, Company company) {
        this.id = id;
        this.type = type;
        this.capacityUnit = capacityUnit;
        this.capacity = capacity;
        this.company = company;
    }

    public Vehicle(int id, String type, CapacityUnit capacityUnit, float capacity, Company company, Set<Order> orders) {
        this(id, type, capacityUnit, capacity, company);
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
        return id == vehicle.id && Float.compare(capacity, vehicle.capacity) == 0 && Objects.equals(type, vehicle.type) && Objects.equals(capacityUnit, vehicle.capacityUnit) && Objects.equals(company, vehicle.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, capacityUnit, capacity, company);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", capacityUnit='" + capacityUnit + '\'' +
                ", capacity=" + capacity +
                ", company=" + company +
                ", orders=" + orders +
                '}';
    }
}
