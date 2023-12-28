package org.example.util.mappers;

import org.example.dto.CreateOrderDto;
import org.example.entity.Order;

public class OrderMapper {
    public static Order dtoToObject(CreateOrderDto orderDto){
        Order order = new Order();
        order.setStartTime(orderDto.getStartTime());
        order.setEndTime(orderDto.getEndTime());
        order.setDeparturePoint(orderDto.getDeparturePoint());
        order.setArrivalPoint(orderDto.getArrivalPoint());
        order.setPrice(orderDto.getPrice());
        order.setPayload(orderDto.getPayload());
        order.setDriver(orderDto.getDriver());
        order.setCompany(orderDto.getCompany());
        order.setClients(orderDto.getClients());
        return order;
    }
}
