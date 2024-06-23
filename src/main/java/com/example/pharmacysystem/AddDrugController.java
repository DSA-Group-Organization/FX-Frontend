package com.example.pharmacysystem;

import com.example.pharmacysystem.models.Drug;
import com.example.pharmacysystem.utils.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddDrugController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField unitPriceField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField supplierField;

    @FXML
    private Button addDrugButton;

    @FXML
    private void handleAddDrugButtonClick() {
        String drug_name = nameField.getText();
        double unit_price = Double.parseDouble(unitPriceField.getText());
        String supplier_name = supplierField.getText();
        int quantity_in_stock = Integer.parseInt(quantityField.getText());

        // Add drug to the database
        Drug newDrug = new Drug(drug_name, unit_price, supplier_name, quantity_in_stock);
        boolean addedSuccessfully = addDrugToDatabase(newDrug);

        // Display confirmation dialog
        if (addedSuccessfully) {
            displaySuccessDialog(drug_name);
        }
    }

    private boolean addDrugToDatabase(Drug drug) {
        String insertQuery = "INSERT INTO drug (drug_name, unit_price, supplier_name, quantity_in_stock) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, drug.getName());
            preparedStatement.setDouble(2, drug.getUnitPrice());
            preparedStatement.setString(3, drug.getSupplier());
            preparedStatement.setInt(4, drug.getQuantity());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error adding drug to database: " + e.getMessage());
            return false;
        }
    }

    private void displaySuccessDialog(String drugName) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Drug Added Successfully");
        alert.setHeaderText(null);
        alert.setContentText(drugName + " added to database successfully.");
        alert.showAndWait();
    }
}
