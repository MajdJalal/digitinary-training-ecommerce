package com.digitinary.task3.service.decorator;


/**
 * a wrapper to add additional logic to the product
 */
public class WarrantyDecorator extends ProductDecorator{
    public WarrantyDecorator(Product product) {
        super(product);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " ,it also has a warranty";
    }
}
