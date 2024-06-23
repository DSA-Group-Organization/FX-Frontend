package com.example.pharmacysystem.models;


public class Drug {
    private String name;
    private double price;
    private String supplier;
    private int quantity;

    public Drug(String name, double price, String supplier, int quantity) {
        this.name = name;
        this.price = price;
        this.supplier = supplier;
        this.quantity = quantity;
    }

    public String getName() { return name; }

    public double getUnitPrice() {
        return price;
    }

    public String getSupplier() {
        return supplier;
    }

    public int getQuantity() {
        return quantity;
    }

    // Add any other necessary methods or properties
}
