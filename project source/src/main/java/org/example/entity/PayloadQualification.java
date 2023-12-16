package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "payload_qualification")
public class PayloadQualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String qualification;

    public PayloadQualification() {
    }

    public PayloadQualification(int id, String qualification) {
        this.id = id;
        this.qualification = qualification;
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
}
