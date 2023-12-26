package org.example.dao;

import org.example.entity.Order;

public class OrderDao extends AbstractDao<Order>{
    public OrderDao() {
        super(Order.class);
    }
}
