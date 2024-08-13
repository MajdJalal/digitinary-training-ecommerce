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
import com.digitinary.task3.service.payment.CreditCardPayment;
import com.digitinary.task3.service.payment.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PaymentFactoryRegistry paymentFactoryRegistry;
    @Mock
    private
    OrderPublisherRegistry orderPublisherRegistry;

    @Mock
    PaymentFactory paymentFactory;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CreditCardPayment creditCardPayment;


    @InjectMocks
    private OrderService orderService; //

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateOrder_UserNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            orderService.createOrder(1L, new OrderRequestDto(), "credit");
        });

        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    void testCreateOrder_PaymentFailed() {
        User user = new User();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(paymentFactory.createPayment()).thenReturn(creditCardPayment); // Mocking a payment failure
        when(creditCardPayment.processPayment(user)).thenReturn(false);
        assertThrows(RuntimeException.class, () -> {
            orderService.createOrder(1L, new OrderRequestDto(), "credit");
        });

    }

    @Test
    void testCreateOrder_Success() {
        User user = new User();
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        Order order = new Order();

        PaymentFactory paymentFactory = mock(PaymentFactory.class);
        PaymentFactoryRegistry.factoryMap.put("credit", paymentFactory);
        when(paymentFactory.createPayment()).thenReturn(creditCardPayment);
        when(creditCardPayment.processPayment(user)).thenReturn(true);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(orderMapper.toOrder(orderRequestDto)).thenReturn(order);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        orderService.createOrder(1L, orderRequestDto, "credit");

        verify(userRepository).findById(anyLong());
        verify(paymentFactory.createPayment()).processPayment(user);
        verify(orderMapper).toOrder(orderRequestDto);
        verify(orderRepository).save(order);
        assertNotNull(OrderPublisherRegistry.orderPublisherMap.get(order.getId()));
        assertNotNull(UserSubsciberRegistry.userSubscriberMap.get(1L));
    }



    @Test
    void testShipOrder_OrderNotFound() {
        Long orderId = 1L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> orderService.shipOrder(orderId));

        verify(orderRepository, times(1)).findById(orderId);
    }


    @Test
    void testShipOrder_Success() {
        Order order = new Order();
        order.setId(1L);
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));

        OrderPublisher orderPublisher = mock(OrderPublisher.class);
        OrderPublisherRegistry.orderPublisherMap.put(1L, orderPublisher);

        orderService.shipOrder(1L);

        verify(orderRepository).findById(anyLong());
        verify(orderPublisher, times(1)).notifySubscribers();
        verify(orderRepository).save(order);
        assertEquals(OrderStatus.Shipped, order.getOrderStatus());
    }
    @Test
    void testGetProductDescription_NoDecorators() {
        String description = orderService.getProductDescription(false, false);
        assertEquals(new BasicProduct().getDescription(), description);
    }

    @Test
    void testGetProductDescription_WithGiftDecorator() {
        String description = orderService.getProductDescription(true, false);
        assertEquals(new GiftDecorator(new BasicProduct()).getDescription(), description);
    }

    @Test
    void testGetProductDescription_WithWarrantyDecorator() {
        String description = orderService.getProductDescription(false, true);
        assertEquals(new WarrantyDecorator((new BasicProduct())).getDescription(), description);
    }

    @Test
    void testGetProductDescription_WithGiftAndWarrantyDecorators() {
        String description = orderService.getProductDescription(true, true);
        assertEquals(new WarrantyDecorator(new GiftDecorator(new BasicProduct())).getDescription(), description);
    }





}
