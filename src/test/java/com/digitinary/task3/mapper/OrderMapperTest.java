package com.digitinary.task3.mapper;

import com.digitinary.task3.dto.OrderRequestDto;
import com.digitinary.task3.entity.Order;
import com.digitinary.task3.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest {

    private OrderMapper orderMapper;

    @BeforeEach
    void setUp() {
        orderMapper = new OrderMapper();
    }

    @Test
    void testToOrder_ShouldMapFieldsCorrectly() {
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setDescription("Test Description");
        orderRequestDto.setOrderStatus(OrderStatus.Processing);

        Order order = orderMapper.toOrder(orderRequestDto);

        assertEquals("Test Description", order.getDscription());
        assertEquals(OrderStatus.Processing, order.getOrderStatus());
    }

    @Test
    void testToOrder_ShouldHandleNullFields() {
        // Arrange
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setDescription(null);
        orderRequestDto.setOrderStatus(null);

        // Act
        Order order = orderMapper.toOrder(orderRequestDto);

        // Assert
        assertEquals(null, order.getDscription());
        assertEquals(null, order.getOrderStatus());
    }

}