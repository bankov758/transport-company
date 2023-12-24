package org.example.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "client")
public class Client extends Person {

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Order> orders;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<Receipt> receipts;

    public Client() {
    }

    public Client(int id, String firstName, String lastName, String ssn) {
        super(id, firstName, lastName, ssn);
    }

    public Client(int id, String firstName, String lastName, Set<Order> orders, Set<Receipt> receipts, String ssn) {
        this(id, firstName, lastName, ssn);
        this.orders = orders;
        this.receipts = receipts;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(Set<Receipt> receipts) {
        this.receipts = receipts;
    }

}
