package org.example.entity;

import javax.persistence.*;

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

    @ManyToOne
    private PayloadQualification payloadQualification;

    public Payload() {
    }

    public Payload(int id, String unit, float unitValue, PayloadQualification payloadQualification) {
        this.id = id;
        this.unit = unit;
        this.unitValue = unitValue;
        this.payloadQualification = payloadQualification;
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

}
