package com.digitinary.task3.mapper;


import com.digitinary.task3.dto.OrderRequestDto;
import com.digitinary.task3.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order toOrder(OrderRequestDto orderRequestDto) {
        return Order.builder()
                .dscription(orderRequestDto.getDescription())
                .orderStatus(orderRequestDto.getOrderStatus())
                .build();
    }
}
