package com.digitinary.task3.service.factory;

import java.util.HashMap;
import java.util.Map;

public class PaymentFactoryRegistry {
    public static Map<String, PaymentFactory> factoryMap = new HashMap<>();
    //put default factories;
    static {
        factoryMap.put("credit", new CreditCardPaymentFactory());
        factoryMap.put("paypal", new PayPalPaymentFactory());
    }

    /**
     * @param paymentMethod
     * @return PaymentFactory, based on the value passed
     * @throws IllegalArgumentException if no factory for the paymentMethod passed
     * @author Majd Alkhawaja
     */
    public static PaymentFactory getPaymentFactory(String paymentMethod) {
        PaymentFactory factory = factoryMap.get(paymentMethod);
        if (factory == null) {
            throw new IllegalArgumentException("Unknown payment type: " + paymentMethod);
        }
        return factory;
    }

}
