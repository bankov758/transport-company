package org.example.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "receipt",
        uniqueConstraints = @UniqueConstraint(columnNames = {"client_id", "order_id"}))
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Client client;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Order order;

    public Receipt() {
    }

    public Receipt(long id, Client client, Order order) {
        this.id = id;
        this.client = client;
        this.order = order;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
        if (!(o instanceof Receipt receipt)) return false;
        return id == receipt.id && Objects.equals(client, receipt.client) && Objects.equals(order, receipt.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, order);
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", client=" + client.getFirstName() + " " + client.getLastName() +
                ", order=" + order.getId() +
                '}';
    }
}
