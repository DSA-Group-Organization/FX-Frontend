package com.example.pharmacysystem;

import com.example.pharmacysystem.models.Supplier;
import com.example.pharmacysystem.utils.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddSupplierController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField idField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField drugField;

    @FXML
    private Button addSupplierButton;

    @FXML
    private void handleAddSupplierButtonClick() {
        int supplier_id = Integer.parseInt(idField.getText());
        String supplier_name = nameField.getText();
        String supplier_email = emailField.getText();
        String drug_name = drugField.getText();

        // Add supplier to the database
        Supplier newSupplier = new Supplier(supplier_id, supplier_name, supplier_email, drug_name);
        boolean addedSuccessfully = addSupplierToDatabase(newSupplier);

        // Display confirmation dialog
        if (addedSuccessfully) {
            displaySuccessDialog();
        } else {
            displayErrorDialog();
        }
    }

    private boolean addSupplierToDatabase(Supplier supplier) {
        String insertQuery = "INSERT INTO supplier (supplier_id, supplier_name, supplier_email, drug_name) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setInt(1, supplier.getId());
            preparedStatement.setString(2, supplier.getName());
            preparedStatement.setString(3, supplier.getEmail());
            preparedStatement.setString(4, supplier.getDrugName());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error adding supplier to database: " + e.getMessage());
            return false;
        }
    }

    private void displaySuccessDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Supplier Added Successfully");
        alert.setHeaderText(null);
        alert.setContentText("Supplier added to database successfully.");
        alert.showAndWait();
    }

    private void displayErrorDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Adding Supplier");
        alert.setHeaderText(null);
        alert.setContentText("Error adding supplier to database. Please check your input and try again.");
        alert.showAndWait();
    }
}
