package com.digitinary.task3.service.payment;

import com.digitinary.task3.entity.User;
import com.digitinary.task3.service.strategy.DiscountStrategy;
import com.digitinary.task3.service.strategy.DiscountStrategyRegistry;
import com.digitinary.task3.service.strategy.FixedAmountDiscountStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PayPalPayment implements Payment{

    private final Logger logger = LoggerFactory.getLogger(PayPalPayment.class);
    PaymentGateway paymentGateway = PaymentGateway.getInstance();
    @Override
    public boolean processPayment(User user) {

        boolean isAuthorized = paymentGateway.authorizePayment(user);
        if(!isAuthorized) throw new RuntimeException("unauthorized user");
        //apply discount
        DiscountStrategy discountStrategy = DiscountStrategyRegistry.discountStrategyMap.get(user.getDiscountType());
        discountStrategy.execute();
        logger.info("process payment(paypal)");
        return true;
    }
}
