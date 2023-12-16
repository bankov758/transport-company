package org.example.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client extends Person {
    public Client() {
    }

    public Client(int id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }
}
