package com.digitinary.task3.service.strategy;

import com.digitinary.task3.enums.DiscountType;

import java.util.HashMap;
import java.util.Map;

/**
 * to map discount type to discount strategy , so retrieval of the strategy becomes easier
 */
public class DiscountStrategyRegistry {
    public static Map<DiscountType, DiscountStrategy> discountStrategyMap = new HashMap<>();

    static  {
        discountStrategyMap.put(DiscountType.PercentageDiscount, new PercentageDiscountStrategy());
        discountStrategyMap.put(DiscountType.FixedAmountDiscount, new FixedAmountDiscountStrategy());

    }


}
