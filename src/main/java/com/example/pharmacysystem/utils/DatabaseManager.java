package com.example.pharmacysystem.utils;

import com.example.pharmacysystem.models.Drug;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseManager {
    private Map<String, Integer> drugInventory;

    public DatabaseManager() {
        drugInventory = new HashMap<>();
        loadDrugInventory();
    }

    private void loadDrugInventory() {
        String query = "SELECT drug_name, quantity_in_stock FROM drug";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("drug_name");
                int quantity = rs.getInt("quantity_in_stock");
                drugInventory.put(name, quantity);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getDrugQuantity(String name) throws Exception {
        if (!drugInventory.containsKey(name)) {
            throw new Exception("Drug not found");
        }
        return drugInventory.get(name);
    }

    public void updateDrugQuantity(String name, int quantity) throws Exception {
        if (!drugInventory.containsKey(name)) {
            throw new Exception("Drug not found");
        }
        int currentQuantity = drugInventory.get(name);
        if (quantity > currentQuantity) {
            throw new Exception("Insufficient quantity");
        }
        drugInventory.put(name, currentQuantity - quantity);
        updateDatabase(name, currentQuantity - quantity);
    }

    private void updateDatabase(String name, int newQuantity) {
        String query = "UPDATE drug SET quantity_in_stock = ? WHERE drug_name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, newQuantity);
            stmt.setString(2, name);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Drug> getAllDrugs() {
        List<Drug> drugs = new ArrayList<>();
        String query = "SELECT drug_name, unit_price, supplier_name, quantity_in_stock FROM drug";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String name = resultSet.getString("drug_name");
                double price = resultSet.getDouble("unit_price");
                String supplier = resultSet.getString("supplier_name");
                int quantity = resultSet.getInt("quantity_in_stock");

                drugs.add(new Drug(name, price, supplier, quantity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return drugs;
    }
}
