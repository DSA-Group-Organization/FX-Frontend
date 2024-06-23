package com.example.pharmacysystem.models;

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

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
