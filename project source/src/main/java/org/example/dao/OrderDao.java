package org.example.dao;

import org.example.configuration.HibernateConfig;
import org.example.entity.Order;
import org.example.entity.Receipt;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OrderDao extends AbstractDao<Order>{
    public OrderDao() {
        super(Order.class);
    }

    public long getNumberOfOrderClients(int orderId) {
        long employees;
        try (Session session =  HibernateConfig.getSessionFactory().openSession()) {
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
        try (Session session =  HibernateConfig.getSessionFactory().openSession()) {
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

    public Long getNumberOfOrdersForCompany(String name) {
        Long numOfOrders;
        try (Session session =  HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            numOfOrders = session.createQuery(
                            " select count(*) from Order o " +
                                    " join o.company c " +
                                    " where c.name = :name " +
                                    " and o.endTime <= current timestamp ",
                            Long.class)
                    .setParameter("name", name)
                    .getSingleResult();
            transaction.commit();
        }
        return numOfOrders;
    }

    /**
     * Get the income of (already paid and finished) orders for a given company
     */
    public Double getIncomeFromOrdersForCompany(String name) {
        Double numOfOrders;
        try (Session session =  HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            numOfOrders = session.createQuery(
                            " select sum(o.price) from Order o " +
                                    " join o.company c " +
                                    " join o.receipts r " +
                                    " where c.name = :name " +
                                    " and o.endTime <= current timestamp ",
                            Double.class)
                    .setParameter("name", name)
                    .getSingleResult();
            transaction.commit();
        }
        return numOfOrders;
    }


}
