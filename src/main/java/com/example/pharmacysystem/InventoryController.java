package com.example.pharmacysystem;

import com.example.pharmacysystem.models.Drug;
import com.example.pharmacysystem.utils.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.List;

public class InventoryController {
    @FXML
    private TableView<Drug> drugTableView;

    @FXML
    private TableColumn<Drug, String> nameColumn;

    @FXML
    private TableColumn<Drug, Double> priceColumn;

    @FXML
    private TableColumn<Drug, String> supplierColumn;

    @FXML
    private TableColumn<Drug, Integer> quantityColumn;

    @FXML
    private Button addDrugButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    private DatabaseManager databaseManager;

    @FXML
    private void initialize() {
        databaseManager = new DatabaseManager();

        // Set up the table columns
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        supplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        // Populate the table with data from the database
        loadDrugsFromDatabase();
    }

    private void loadDrugsFromDatabase() {
        List<Drug> drugList = databaseManager.getAllDrugs();
        ObservableList<Drug> drugs = FXCollections.observableArrayList(drugList);
        drugTableView.setItems(drugs);
    }

    @FXML
    private void handleAddDrugButtonClick() {
        loadFXML("addDrug-view.fxml");
    }

    @FXML
    private void handleDeleteButtonClick() {
        loadFXML("buy-drug-view.fxml");
    }

    @FXML
    private void handleUpdateButtonClick() {
        // Implement the logic to update an existing drug
    }

    private void loadFXML(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();
            Stage stage = (Stage) addDrugButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
