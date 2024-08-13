package com.digitinary.task3.service.observer;

import com.digitinary.task3.service.factory.PaymentFactory;

import java.util.HashMap;
import java.util.Map;

public class UserSubsciberRegistry {

    public static Map<Long, UserSubscriber> userSubscriberMap = new HashMap<>();
}
