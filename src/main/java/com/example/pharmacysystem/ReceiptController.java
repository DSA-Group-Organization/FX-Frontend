package com.example.pharmacysystem;

import com.example.pharmacysystem.utils.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptController {
    @FXML
    private TableView<OrderItem> orderTableView;

    @FXML
    private TableColumn<OrderItem, String> nameColumn;

    @FXML
    private TableColumn<OrderItem, Integer> quantityColumn;

    @FXML
    private TableColumn<OrderItem, Double> priceColumn;

    @FXML
    private TableColumn<OrderItem, Double> totalColumn;

    @FXML
    private TableColumn<OrderItem, String> timeColumn;

    @FXML
    private Label totalLabel;

    @FXML
    private Button printButton;

    @FXML
    private void initialize() {
        // Set up the table columns
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp"));

        // Populate the table with data from the database
        ObservableList<OrderItem> orderItems = getOrderItemsFromDatabase();
        orderTableView.setItems(orderItems);

        // Calculate and display the total
        double total = orderItems.stream()
                .mapToDouble(OrderItem::getTotal)
                .sum();
        totalLabel.setText(String.format("[%.2f]", total));
    }

    private ObservableList<OrderItem> getOrderItemsFromDatabase() {
        ObservableList<OrderItem> orderItems = FXCollections.observableArrayList();

        String query = "SELECT drug_name, qty_bought, unit_price, (qty_bought * unit_price) AS total, time FROM receipt";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String name = resultSet.getString("drug_name");
                int quantity = resultSet.getInt("qty_bought");
                double price = resultSet.getDouble("unit_price");
                double total = resultSet.getDouble("total");
                LocalDateTime timestamp = resultSet.getTimestamp("time").toLocalDateTime();

                orderItems.add(new OrderItem(name, quantity, price, total, timestamp));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching data from database: " + e.getMessage());
        }

        return orderItems;
    }

    @FXML
    private void handlePrintButtonClick() {
        // Implement the logic to print the receipt
    }
}
