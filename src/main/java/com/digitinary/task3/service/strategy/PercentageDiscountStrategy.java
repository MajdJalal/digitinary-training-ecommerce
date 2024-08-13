package com.digitinary.task3.service.strategy;

import com.digitinary.task3.service.impl.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * applies specific logic for discount
 */
public class PercentageDiscountStrategy implements DiscountStrategy{
    private final Logger logger = LoggerFactory.getLogger(PercentageDiscountStrategy.class);
    @Override
    public void execute() {
        logger.info("apply percentage discount");
    }
}
