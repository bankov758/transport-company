package org.example.service;

import org.example.dao.ClientDao;
import org.example.dao.OrderDao;
import org.example.dao.ReceiptDao;
import org.example.entity.Client;
import org.example.entity.Order;
import org.example.entity.Receipt;

import java.util.HashSet;
import java.util.Set;

public class OrderService {

    private final OrderDao orderDao;
    private final ClientDao clientDao;
    private final ReceiptDao receiptDao;

    public OrderService() {
        orderDao = new OrderDao();
        clientDao = new ClientDao();
        receiptDao = new ReceiptDao();
    }

    /**
     * Adds a client to an order using the client's name.
     *
     * @param order The order to which the client will be added.
     * @param name  The name of the client to be added.
     */
    public void addClient(Order order, String name){
        Client client = clientDao.getByField("firstName", name);
        addClient(order, client);
    }

    /**
     * Adds a client to an order.
     *
     * @param order  The order to which the client will be added.
     * @param client The client to be added.
     */
    public void addClient(Order order, Client client){
        Set<Client> clients = order.getClients() == null ? new HashSet<>() : order.getClients();
        clients.add(client);
        order.setClients(clients);
        orderDao.update(order);
    }

    /**
     * Pays for an order using the client's name.
     *
     * @param orderId The id of the order to be paid.
     * @param name    The name of the client making the payment.
     */
    public void pay(int orderId, String name) {
        Client client = clientDao.getByField("firstName", name);
        Order order = orderDao.getById(orderId);
        pay(order, client);
    }

    /**
     * Pays for an order.
     *
     * @param order  The order for which the payment will be made.
     * @param client The client making the payment.
     */
    public void pay(Order order, Client client) {
        Receipt receipt = new Receipt();
        receipt.setOrder(order);
        receipt.setClient(client);
        receiptDao.create(receipt);
    }

    /**
     * Checks whether an order has been fully paid by comparing the number of clients and receipts associated with it.
     *
     * @param orderId The id of the order to be checked for payment status.
     * @return True if the order is fully paid; false otherwise.
     */
    public boolean isOrderPayed(int orderId){
        long clients = orderDao.getNumberOfOrderClients(orderId);
        long receipts = orderDao.getNumberOfOrderReceipts(orderId);
        return clients == receipts;
    }

}
