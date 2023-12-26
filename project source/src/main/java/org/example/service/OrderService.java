package org.example.service;

import org.example.dao.ClientDao;
import org.example.dao.OrderDao;
import org.example.entity.Client;
import org.example.entity.Order;

import java.util.HashSet;
import java.util.Set;

public class OrderService {

    private OrderDao orderDao;

    private ClientDao clientDao;

    public OrderService() {
        orderDao = new OrderDao();
        clientDao = new ClientDao();
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

    public void pay(Order order){

    }

}
