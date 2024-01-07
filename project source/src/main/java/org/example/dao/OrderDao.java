package org.example.dao;

import org.example.configuration.HibernateConfig;
import org.example.entity.Company;
import org.example.entity.Order;
import org.example.entity.PayloadQualification;
import org.example.entity.Receipt;
import org.example.entity.enumeration.OrderBy;
import org.example.exception.InvalidBusinessData;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class OrderDao extends AbstractDao<Order> {

    public OrderDao() {
        super(Order.class);
    }

    @Override
    public void create(Order order) {
        checkOrderData(order);
        super.create(order);
    }

    @Override
    public void update(Order order) {
        checkOrderData(order);
        super.update(order);
    }

    private static void checkOrderData(Order order) {
        PayloadQualification orderPayloadQlf = order.getPayload().getPayloadQualification();
        if (!order.getDriver().getPayloadQualifications().contains(orderPayloadQlf)) {
            throw new InvalidBusinessData("Driver is not qualified for order payload - " + orderPayloadQlf.getQualification());
        }
        if (!order.getPayload().getUnit().equals(order.getVehicle().getCapacityUnit())
                || order.getPayload().getUnitValue() > order.getVehicle().getCapacity()){
            throw new InvalidBusinessData("Vehicle and payload are not compatible!");
        }
        if (!order.getCompany().equals(order.getVehicle().getCompany())){
            throw new InvalidBusinessData("Vehicle and order are not of the same company!");
        }
    }

    public long getNumberOfOrderClients(int orderId) {
        long employees;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employees = session.createQuery(
                            "select c " +
                                    " from Client c" +
                                    " join c.orders o " +
                                    "where o.id = :orderId",
                            Class.class)
                    .setParameter("orderId", orderId)
                    .getResultStream().count();
            transaction.commit();
        }
        return employees;
    }

    public long getNumberOfOrderReceipts(int orderId) {
        long employees;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employees = session.createQuery(
                            "select r " +
                                    " from Receipt r" +
                                    " join r.order o " +
                                    "where o.id = :orderId",
                            Receipt.class)
                    .setParameter("orderId", orderId)
                    .getResultStream().count();
            transaction.commit();
        }
        return employees;
    }

    public Long getNumberOfOrdersForCompany(String companyName) {
        Long numOfOrders;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            numOfOrders = session.createQuery(
                            " select count(*) from Order o " +
                                    " join o.company c " +
                                    " where c.name = :companyName " +
                                    " and o.endTime <= current timestamp ",
                            Long.class)
                    .setParameter("companyName", companyName)
                    .getSingleResult();
            transaction.commit();
        }
        return numOfOrders;
    }

    /**
     * Get the income of (already paid and finished) orders for a given company
     */
    public Double getIncomeFromOrdersForCompany(String companyName) {
        Double numOfOrders;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            numOfOrders = session.createQuery(
                            " select sum(o.price) from Order o " +
                                    " join o.company c " +
                                    " join o.receipts r " +
                                    " where c.name = :companyName " +
                                    " and o.endTime <= current timestamp ",
                            Double.class)
                    .setParameter("companyName", companyName)
                    .getSingleResult();
            transaction.commit();
        }
        return numOfOrders;
    }

    public List<Order> filterByDestination(String destination) {
        List<Order> orders;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            orders = session.createQuery("select c from Order c" +
                                    " where c.arrivalPoint = :destination"
                            , Order.class)
                    .setParameter("destination", destination)
                    .getResultList();
            transaction.commit();
        }
        return orders;
    }

}
