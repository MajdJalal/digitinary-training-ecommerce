package com.digitinary.task3.service.factory;

import com.digitinary.task3.service.payment.PayPalPayment;
import com.digitinary.task3.service.payment.Payment;

/**
 * factory responsible for creating paypal payment methods
 */
public class PayPalPaymentFactory implements PaymentFactory{
    @Override
    public Payment createPayment() {
        return new PayPalPayment();
    }
}
