package org.example.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "payload_qualification",
        uniqueConstraints = @UniqueConstraint(columnNames = {"qualification"}))
public class PayloadQualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "qualification")
    private String qualification;

    @OneToMany(mappedBy = "payloadQualification", fetch = FetchType.LAZY)
    private Set<Payload> payloads;

    @ManyToMany(fetch = FetchType.LAZY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PayloadQualification that)) return false;
        return id == that.id && Objects.equals(qualification, that.qualification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, qualification);
    }

    @Override
    public String toString() {
        return "PayloadQualification{" +
                "id=" + id +
                ", qualification='" + qualification + '\'' +
                ", payloads=" + payloads +
                ", employees=" + employees +
                '}';
    }
}
