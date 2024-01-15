package com.example.handler.exception;

public class OrderNotFoundExcpetion extends RuntimeException {
    public OrderNotFoundExcpetion() {
        super("Order not found.");
    }
}
