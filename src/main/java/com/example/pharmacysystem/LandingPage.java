package com.example.pharmacysystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LandingPage {
    @FXML
    private Button addDrugButton;

    @FXML
    private Button buyDrugButton;

    @FXML
    private Button addSupplierButton;

    @FXML
    private Button displayDrugsButton;

    @FXML
    private Button displaySuppliersButton;

    @FXML
    private Button displayReceiptButton;

    @FXML
    private void handleAddDrugButtonClick() {
        // Route to addDrug-view.fxml
        loadFXML("addDrug-view.fxml");
    }

    @FXML
    private void handleBuyDrugButtonClick() {
        // Route to buy-drug-view.fxml
        loadFXML("buy-drug-view.fxml");
    }

    @FXML
    private void handleAddSupplierButtonClick() {
        // Route to add-supplier-view.fxml
        loadFXML("add-supplier-view.fxml");
    }

    @FXML
    private void handleDisplayDrugsButtonClick() {
        // Route to inventory-view.fxml
        loadFXML("inventory-view.fxml");
    }

    @FXML
    private void handleDisplaySuppliersButtonClick() {
        // Route to display-supplier-view.fxml
        loadFXML("supplier-view.fxml");
    }

    @FXML
    private void handleDisplayReceiptButtonClick() {
        // Route to receipt-view.fxml
        loadFXML("receipt-view.fxml");
    }

    // Helper method to load FXML file and set as the current scene
    private void loadFXML(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();
            Stage stage = (Stage) addDrugButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
