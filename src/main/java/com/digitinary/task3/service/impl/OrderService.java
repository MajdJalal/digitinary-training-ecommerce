package com.digitinary.task3.service.impl;

import com.digitinary.task3.dto.OrderRequestDto;
import com.digitinary.task3.entity.Order;
import com.digitinary.task3.entity.User;
import com.digitinary.task3.enums.OrderStatus;
import com.digitinary.task3.exception.NotFoundException;
import com.digitinary.task3.mapper.OrderMapper;
import com.digitinary.task3.repository.OrderRepository;
import com.digitinary.task3.repository.UserRepository;
import com.digitinary.task3.service.decorator.BasicProduct;
import com.digitinary.task3.service.decorator.GiftDecorator;
import com.digitinary.task3.service.decorator.Product;
import com.digitinary.task3.service.decorator.WarrantyDecorator;
import com.digitinary.task3.service.factory.PaymentFactory;
import com.digitinary.task3.service.factory.PaymentFactoryRegistry;
import com.digitinary.task3.service.observer.OrderPublisher;
import com.digitinary.task3.service.observer.OrderPublisherRegistry;
import com.digitinary.task3.service.observer.UserSubsciberRegistry;
import com.digitinary.task3.service.observer.UserSubscriber;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class OrderService {

    private  final UserRepository userRepository;
    private  final OrderRepository orderRepository;
    private  final OrderMapper orderMapper;

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);
    /**
     * @param userId
     * @param orderRequestDto
     * @param paymentMethod
     * first, it checks if the exists or not,
     * if exists, we create a payment method based on the values 'paymentMethod'
     * then we let the paymentMethod object to process the payment,
     * if success, then we get an order from the ordeRrequestDto passed
     * we then create an orderPublisher , put it in the registry for future reference from the order id (as this publisher is triggered by an order)
     * we then create a userSubscriber if it doesn't exist already
     * and we put the userSubscriber as a subscriber in the orderPublisher's subscribers list
     * and finally we save the order
     * @author Majd Alkhawaja
     */
    public void createOrder(Long userId, OrderRequestDto orderRequestDto, String paymentMethod) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) throw new NotFoundException("no user with this id");
        User user = optionalUser.get();
        PaymentFactory paymentFactory = PaymentFactoryRegistry.getPaymentFactory(paymentMethod);
        boolean successful = paymentFactory.createPayment().processPayment(user);
        if(!successful) throw new RuntimeException("payment was not successful");
        Order order = orderMapper.toOrder(orderRequestDto);
        order.setUser(user);
        order = orderRepository.save(order);
        OrderPublisher orderPublisher = new OrderPublisher(); // a new order so a new publisher
        OrderPublisherRegistry.orderPublisherMap.put(order.getId(), orderPublisher);
        UserSubscriber userSubscriber = UserSubsciberRegistry.userSubscriberMap.computeIfAbsent(userId, k -> new UserSubscriber(user));
        orderPublisher.subscribe(userSubscriber);
    }

    /**
     * @param orderId
     * first we find the order asscoiated with the passed id
     * if doesn't exist
     * @throws NotFoundException
     * we change the orderStatus to shipped
     * get the orderPublisher associated with this order
     * @author Majd Alkhawaja
     */
    public void shipOrder(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isEmpty()) throw new NotFoundException("no order with this id");
        Order order = optionalOrder.get();
        order.setOrderStatus(OrderStatus.Shipped);
        OrderPublisher orderPublisher  = OrderPublisherRegistry.orderPublisherMap.get(orderId);
        orderPublisher.notifySubscribers();
        orderRepository.save(order);
    }

    /**
     * @param isGift
     * @param hasWarranty
     * @return the description of the product
     * first, it initializes a basic product
     * then checks if it is a gift it wraps it into a decorator to add gift functionality
     * and if it has warranty it also wraps it into a warranty decorator to add the warranty functionality
     * @author Majd Alkhawaja
     */
    public String getProductDescription(boolean isGift, boolean hasWarranty) {
        Product product = new BasicProduct();
        if(isGift) product = new GiftDecorator(product);
        if(hasWarranty) product = new WarrantyDecorator(product);
        return product.getDescription();
    }


}
