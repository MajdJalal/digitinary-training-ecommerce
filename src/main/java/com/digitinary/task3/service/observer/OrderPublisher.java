package com.digitinary.task3.service.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * publish to its subscribers on a trigger
 */
public class OrderPublisher implements Publisher{

    private final Logger logger = LoggerFactory.getLogger(OrderPublisher.class);

    List<Subscriber> subscribers = new ArrayList<>();

    public void subscribe(Subscriber subscriber) {
        logger.info("subscribe a new subscriber");
        subscribers.add(subscriber);

    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void notifySubscribers() {
        subscribers.forEach(subscriber -> {
            logger.info("notify subscribers");
            subscriber.update();
        });
    }




}
