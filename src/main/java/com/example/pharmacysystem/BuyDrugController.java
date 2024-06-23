package com.example.pharmacysystem;

import com.example.pharmacysystem.utils.DatabaseManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BuyDrugController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField quantityField;

    @FXML
    private Button buyDrugButton;

    @FXML
    private Button removeDrugButton;

    private DatabaseManager dbManager;

    public BuyDrugController() {
        dbManager = new DatabaseManager();
    }

    @FXML
    private void handleBuyDrugButtonClick() {
        String name = nameField.getText();
        int quantity;

        try {
            quantity = Integer.parseInt(quantityField.getText());
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "All input fields are required.");
            return;
        }

        // Implement your buy drug logic here
        buyDrug(name, quantity);
    }

    private void buyDrug(String name, int quantity) {
        try {
            int availableQuantity = dbManager.getDrugQuantity(name);
            dbManager.updateDrugQuantity(name, quantity);
            showAlert("Success", "Bought " + quantity + " of " + name + ". Remaining: " + (availableQuantity - quantity));
        } catch (Exception e) {
            showAlert("Error", e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
