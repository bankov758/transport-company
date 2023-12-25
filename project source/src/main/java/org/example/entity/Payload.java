package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

@Entity
@Table(name = "payload")
public class Payload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit", nullable = false)
    private CapacityUnit unit;

    @Column(name = "unitValue", nullable = false)
    @Positive(message = "Payload's unit value should be a positive number!")
    private float unitValue;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PayloadQualification payloadQualification;

    @OneToOne(fetch = FetchType.LAZY)
    private Order order;

    public Payload() {
    }

    public Payload(int id, CapacityUnit unit, float unitValue, PayloadQualification payloadQualification) {
        this.id = id;
        this.unit = unit;
        this.unitValue = unitValue;
        this.payloadQualification = payloadQualification;
    }

    public Payload(int id, CapacityUnit unit, float unitValue, PayloadQualification payloadQualification, Order order) {
        this(id, unit, unitValue, payloadQualification);
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CapacityUnit getUnit() {
        return unit;
    }

    public void setUnit(CapacityUnit unit) {
        this.unit = unit;
    }

    public float getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(float unitValue) {
        this.unitValue = unitValue;
    }

    public PayloadQualification getPayloadQualification() {
        return payloadQualification;
    }

    public void setPayloadQualification(PayloadQualification payloadQualification) {
        this.payloadQualification = payloadQualification;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payload payload)) return false;
        return id == payload.id && Float.compare(unitValue, payload.unitValue) == 0 && Objects.equals(unit, payload.unit) && Objects.equals(payloadQualification, payload.payloadQualification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, unit, unitValue, payloadQualification);
    }

    @Override
    public String toString() {
        return "Payload{" +
                "id=" + id +
                ", unit='" + unit + '\'' +
                ", unitValue=" + unitValue +
                '}';
    }
}
