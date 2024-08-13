package com.digitinary.task3.service.payment;

import com.digitinary.task3.entity.User;
import com.digitinary.task3.service.impl.OrderService;
import com.digitinary.task3.service.strategy.DiscountStrategy;
import com.digitinary.task3.service.strategy.DiscountStrategyRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreditCardPayment implements Payment{
    PaymentGateway paymentGateway = PaymentGateway.getInstance();
    private final Logger logger = LoggerFactory.getLogger(CreditCardPayment.class);
    @Override
    public boolean processPayment(User user) {
        boolean isAuthorized = paymentGateway.authorizePayment(user);
        if(!isAuthorized) throw new RuntimeException("unauthorized user");
        //apply discount
        DiscountStrategy discountStrategy = DiscountStrategyRegistry.discountStrategyMap.get(user.getDiscountType());
        discountStrategy.execute();
        //TO-DO
        logger.info("process payment(credit card)");
        return true;
    }
}
