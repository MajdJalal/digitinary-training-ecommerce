package com.digitinary.task3.service.decorator;


/**
 * a wrapper to add additional logic to the product
 */
public class GiftDecorator extends ProductDecorator{

    public GiftDecorator(Product product) {
        super(product);
    }

    @Override
    public String getDescription() {
        return super.getDescription() +" ,that is also a gift ";
    }
}
