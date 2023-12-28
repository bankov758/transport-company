package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee extends Person {

    @ManyToOne(optional = false)
    private Company company;

    @Column(name = "salary")
    @Positive(message = "Employee's salary should be a positive number!")
    private float salary;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<PayloadQualification> payloadQualifications;

    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
    private Set<Order> orders;

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName, String ssn, float salary, Company company) {
        super(id, firstName, lastName, ssn);
        this.company = company;
        this.salary = salary;
    }

    public Employee(int id, String firstName, String lastName, String ssn, float salary, Company company,
                    Set<PayloadQualification> payloadQualifications, Set<Order> orders) {
        this(id, firstName, lastName, ssn, salary, company);
        this.payloadQualifications = payloadQualifications;
        this.orders = orders;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<PayloadQualification> getPayloadQualifications() {
        return payloadQualifications;
    }

    public void setPayloadQualifications(Set<PayloadQualification> payloadQualifications) {
        this.payloadQualifications = payloadQualifications;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Employee{" +
                super.toString() +
                ", salary=" + salary +
                ", company=" + company.getName() +
                ", PQ=" + payloadQualifications +
                '}';
    }
}
