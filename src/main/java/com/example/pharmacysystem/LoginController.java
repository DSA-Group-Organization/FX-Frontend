package com.example.pharmacysystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private void handleLoginButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Implement your login logic here
        if (authenticateUser(username, password)) {
            // Load the main application window
            loadMainWindow();
        } else {
            // Display an error message or prompt the user to try again
            System.out.println("Invalid username or password.");
        }
    }

    private boolean authenticateUser(String username, String password) {
        // Implement your user authentication logic here
        // For example, you could check against a database or a hardcoded set of credentials
        return username.equals("admin") && password.equals("password");
    }

    private void loadMainWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("landing-view.fxml"));
            Parent root = loader.load();

            // Create a new Stage for the landing view
            Stage landingStage = new Stage();
            landingStage.setScene(new Scene(root));
            landingStage.setTitle("Pharmacy Management System");
            landingStage.show();

            // Close the current login window
            Stage currentStage = (Stage) loginButton.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
