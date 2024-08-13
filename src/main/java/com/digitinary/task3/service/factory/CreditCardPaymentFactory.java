package com.digitinary.task3.service.factory;

import com.digitinary.task3.service.payment.CreditCardPayment;
import com.digitinary.task3.service.payment.Payment;

/**
 * factory responsible for creating credit card payment methods
 */
public class CreditCardPaymentFactory implements PaymentFactory{
    @Override
    public Payment createPayment() {
        return new CreditCardPayment();
    }
}
