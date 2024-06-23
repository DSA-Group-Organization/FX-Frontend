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

public class SignUpController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button signUpButton;

    @FXML
    private void handleSignUpButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Implement your sign-up logic here
        if (registerUser(username, password, confirmPassword)) {
            // Load the main application window
            loadMainWindow();
        } else {
            // Display an error message or prompt the user to try again
            System.out.println("Sign-up failed. Please check your inputs.");
        }
    }

    private boolean registerUser(String username, String password, String confirmPassword) {
        // Implement your user registration logic here
        // For example, you could store the user information in a database
        return password.equals(confirmPassword);
    }

    private void loadMainWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) signUpButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Pharmacy Management System");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
