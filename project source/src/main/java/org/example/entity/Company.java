package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.example.dto.CompanyDto;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "company",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@SqlResultSetMapping(name = CompanyDto.RESULT_SET_MAPPING_NAME,
        classes = {
                @ConstructorResult(targetClass = CompanyDto.class,
                        columns = {@ColumnResult(name = "name"), @ColumnResult(name = "income")}
                )})
public class Company {

    private static final int MIN_NAME_LENGTH = 3;
    private static final int MAX_NAME_LENGTH = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    @Size(
            min = MIN_NAME_LENGTH,
            max = MAX_NAME_LENGTH,
            message = "Company name has to be between " + MIN_NAME_LENGTH + " and " + MAX_NAME_LENGTH + " characters!"
    )
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
