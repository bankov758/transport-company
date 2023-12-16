package org.example.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee extends Person {

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName, Company company) {
        super(id, firstName, lastName);
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
