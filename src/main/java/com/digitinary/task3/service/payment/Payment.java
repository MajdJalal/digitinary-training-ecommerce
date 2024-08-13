package com.digitinary.task3.service.payment;

import com.digitinary.task3.entity.User;

public interface Payment {

    boolean processPayment(User user);
}
