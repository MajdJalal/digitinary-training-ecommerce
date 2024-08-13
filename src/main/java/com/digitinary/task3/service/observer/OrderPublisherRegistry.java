package com.digitinary.task3.service.observer;

import com.digitinary.task3.repository.OrderRepository;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class OrderPublisherRegistry {
    private static  OrderRepository orderRepository;

    public static Map<Long, OrderPublisher> orderPublisherMap = new HashMap<>();


}
