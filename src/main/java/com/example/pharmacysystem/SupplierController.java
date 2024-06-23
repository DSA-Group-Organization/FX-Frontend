package com.example.pharmacysystem;

import com.example.pharmacysystem.models.Supplier;
import com.example.pharmacysystem.utils.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SupplierController {
    @FXML
    private TableView<Supplier> supplierTableView;

    @FXML
    private TableColumn<Supplier, String> nameColumn;

    @FXML
    private TableColumn<Supplier, String> emailColumn;

    @FXML
    private TableColumn<Supplier, String> drugColumn;

    @FXML
    private void initialize() {
        // Set up the table columns
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        drugColumn.setCellValueFactory(new PropertyValueFactory<>("drug"));

        // Populate the table with data from the database
        loadSupplierData();
    }

    private void loadSupplierData() {
        ObservableList<Supplier> suppliers = FXCollections.observableArrayList();

        String query = "SELECT supplier_name, supplier_email, drug_name FROM supplier";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String name = resultSet.getString("supplier_name");
                String email = resultSet.getString("supplier_email");
                String drug = resultSet.getString("drug_name");

                suppliers.add(new Supplier(0, name, email, drug)); // ID is not relevant for this view
            }

        } catch (SQLException e) {
            System.out.println("Error fetching data from database: " + e.getMessage());
        }

        supplierTableView.setItems(suppliers);
    }

    @FXML
    private void handleRefreshButtonClick() {
        // Implementation for handling refresh button click
    }
}
