package org.example.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee extends Person {

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @ManyToMany(mappedBy = "employees", fetch = FetchType.LAZY)
    private Set<PayloadQualification> payloadQualifications;

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName, Company company) {
        super(id, firstName, lastName);
        this.company = company;

    }

    public Employee(int id, String firstName, String lastName, Company company, Set<PayloadQualification> payloadQualifications) {
        this(id, firstName, lastName, company);
        this.payloadQualifications = payloadQualifications;
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
}
