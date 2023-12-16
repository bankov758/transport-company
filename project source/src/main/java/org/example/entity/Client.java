package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "client")
public class Client extends Person {
    public Client() {
    }

    public Client(int id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

}
