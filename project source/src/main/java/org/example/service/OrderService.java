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

    public void addClient(Order order, int clientId){
        Client client = clientDao.getById(clientId);
        addClient(order, client);
    }

    public void addClient(Order order, Client client){
        Set<Client> clients = order.getClients() == null ? new HashSet<>() : order.getClients();
        clients.add(client);
        order.setClients(clients);
        orderDao.update(order);
    }

    public void pay(int orderId, int clientId) {
        Client client = clientDao.getById(clientId);
        Order order = orderDao.getByField("id", String.valueOf(orderId));
        pay(order, client);
    }

    public void pay(Order order, Client client) {
        Receipt receipt = new Receipt();
        receipt.setOrder(order);
        receipt.setClient(client);
        receiptDao.create(receipt);
    }

    public boolean isOrderPayed(int orderId){
        long clients = orderDao.getNumberOfOrderClients(orderId);
        long receipts = orderDao.getNumberOfOrderReceipts(orderId);
        return clients == receipts;
    }

}
