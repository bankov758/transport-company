package org.example.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<Employee> employees;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<Vehicle> vehicles;

    public Company() {
    }

    public Company(long id, String name, Set<Employee> employees, Set<Vehicle> vehicles) {
        this.id = id;
        this.name = name;
        this.employees = employees;
        this.vehicles = vehicles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
