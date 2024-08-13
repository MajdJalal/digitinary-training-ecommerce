package com.digitinary.task3.service.payment;

import com.digitinary.task3.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * this acts as a connection to processing the payments between merchant and client
 * follows the singleton pattern(lazy) to have one connection to payment operations
 */
public class PaymentGateway {

    private final Logger logger = LoggerFactory.getLogger(PaymentGateway.class);
    private static PaymentGateway paymentGateway;


    public static PaymentGateway getInstance() {
        if (paymentGateway == null) {
            synchronized (PaymentGateway.class) {
                if (paymentGateway == null) {
                    paymentGateway = new PaymentGateway();
                }
            }
        }
        return paymentGateway;
    }

    boolean authorizePayment(User user){
        //TO-DO
        logger.info("authorizing the user transaction");
        return true;
    }
}
