package com.example.pharmacysystem.models;

public class Supplier {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String drug;

    public Supplier(int id, String name, String email, String phone, String drug) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.drug = drug;
    }

    // Constructor without the drug for AddSupplierController
    public Supplier(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDrugName() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }
}
