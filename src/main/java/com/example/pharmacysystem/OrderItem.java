package com.example.pharmacysystem;

import java.time.LocalDateTime;

public class OrderItem {
    private String name;
    private int quantity;
    private double price;
    private double total;
    private LocalDateTime timestamp;

    public OrderItem(String name, int quantity, double price, double total, LocalDateTime timestamp) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotal() {
        return total;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}


