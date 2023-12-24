package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "company",
       uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @NotBlank(message = "Company name cannot be blank!")
    private String name;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<Employee> employees;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<Vehicle> vehicles;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<Order> orders;

    public Company() {
    }

    public Company(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Company(long id, String name, Set<Employee> employees, Set<Vehicle> vehicles, Set<Order> orders) {
        this(id, name);
        this.employees = employees;
        this.vehicles = vehicles;
        this.orders = orders;
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

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company company)) return false;
        return id == company.id && Objects.equals(name, company.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
