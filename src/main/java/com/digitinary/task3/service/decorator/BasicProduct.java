package com.digitinary.task3.service.decorator;


/**
 * provides the basic functionalities of the product interface
 */
public class BasicProduct implements Product{
    @Override
    public String getDescription() {
        return "basic product ";
    }
}
