package com.digitinary.task3.service.observer;

import com.digitinary.task3.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * all of its instances get notified from their publisher
 */
public class UserSubscriber implements Subscriber{
    private final Logger logger = LoggerFactory.getLogger(UserSubscriber.class);
    User user;

    public UserSubscriber(User user) {
        this.user = user;
    }

    @Override
    public void update() {
        //sending an email to the user
        logger.info("sending email to the user with email " + user.getEmail());
    }
}
