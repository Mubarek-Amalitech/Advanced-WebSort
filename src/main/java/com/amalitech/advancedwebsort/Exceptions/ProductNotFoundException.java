package com.amalitech.advancedwebsort.Exceptions;

public class ProductNotFoundException  extends  RuntimeException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}