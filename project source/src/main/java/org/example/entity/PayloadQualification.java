package org.example.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "payload_qualification")
public class PayloadQualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String qualification;

    @OneToMany(mappedBy = "payloadQualification", fetch = FetchType.LAZY)
    private Set<Payload> payloads;

    @ManyToMany
    private Set<Employee> employees;

    public PayloadQualification() {
    }

    public PayloadQualification(int id, String qualification, Set<Payload> payloads, Set<Employee> employees) {
        this.id = id;
        this.qualification = qualification;
        this.payloads = payloads;
        this.employees = employees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Set<Payload> getPayloads() {
        return payloads;
    }

    public void setPayloads(Set<Payload> payloads) {
        this.payloads = payloads;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
