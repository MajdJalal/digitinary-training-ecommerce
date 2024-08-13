package com.digitinary.task3.service.decorator;

public class ProductDecorator implements Product{
    private Product product;

    public ProductDecorator(Product product) {
        this.product = product;
    }

    @Override
    public String getDescription() {
        return product.getDescription();
    }
}
