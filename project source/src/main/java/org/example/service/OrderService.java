package org.example.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import org.example.dao.ClientDao;
import org.example.dao.OrderDao;
import org.example.dao.ReceiptDao;
import org.example.entity.Client;
import org.example.entity.Order;
import org.example.entity.Receipt;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
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

    public void addClient(Order order, String name){
        Client client = clientDao.getByField("firstName", name);
        addClient(order, client);
    }

    public void addClient(Order order, Client client){
        Set<Client> clients = order.getClients() == null ? new HashSet<>() : order.getClients();
        clients.add(client);
        order.setClients(clients);
        orderDao.update(order);
    }

    public void pay(int orderId, String name) {
        Client client = clientDao.getByField("firstName", name);
        Order order = orderDao.getById(orderId);
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
