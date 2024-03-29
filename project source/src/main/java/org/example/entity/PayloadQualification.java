package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "payload_qualification",
        uniqueConstraints = @UniqueConstraint(columnNames = {"qualification"}))
public class PayloadQualification {

    private static final int MIN_QLF_LENGTH = 3;
    private static final int MAX_QLF_LENGTH = 20;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "qualification", nullable = false)
    @Size(
            min = MIN_QLF_LENGTH,
            max = MAX_QLF_LENGTH,
            message = "A qualification has to be between " + MIN_QLF_LENGTH + " and " + MAX_QLF_LENGTH + " characters!"
    )
    private String qualification;

    @OneToMany(mappedBy = "payloadQualification", fetch = FetchType.LAZY)
    private Set<Payload> payloads;

    @ManyToMany(mappedBy = "payloadQualifications", fetch = FetchType.LAZY)
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
        return Objects.equals(qualification, that.qualification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(qualification);
    }

    @Override
    public String toString() {
        return "PayloadQualification{" +
                "id=" + id +
                ", qualification='" + qualification +
                '}';
    }
}
