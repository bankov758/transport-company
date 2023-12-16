package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "capacity_unit")
    private String capacityUnit;

    @Column(name = "capacity")
    private float capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    public Vehicle() {
    }

    public Vehicle(int id, String type, String capacityUnit, float capacity, Company company) {
        this.id = id;
        this.type = type;
        this.capacityUnit = capacityUnit;
        this.capacity = capacity;
        this.company = company;
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

    public String getCapacityUnit() {
        return capacityUnit;
    }

    public void setCapacityUnit(String capacityUnit) {
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
}
