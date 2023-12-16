package org.example.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "payload")
public class Payload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "unit")
    private String unit;

    @Column(name = "unitValue")
    private float unitValue;

    @ManyToOne(fetch = FetchType.LAZY)
    private PayloadQualification payloadQualification;

    @OneToOne
    private Order order;

    public Payload() {
    }

    public Payload(int id, String unit, float unitValue, PayloadQualification payloadQualification) {
        this.id = id;
        this.unit = unit;
        this.unitValue = unitValue;
        this.payloadQualification = payloadQualification;
    }

    public Payload(int id, String unit, float unitValue, PayloadQualification payloadQualification, Order order) {
        this(id, unit, unitValue, payloadQualification);
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
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
}
