package com.example.librarymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;


public class EditAccount {

    @FXML private Label error_username;
    @FXML
    private Label error_password;
    @FXML private TextField confirm_password;
    @FXML private TextField new_password;
    @FXML private TextField new_username;

    public void newUsername(ActionEvent event) throws IOException {

    }

    public void newPassword() throws IOException {
        String newPassword = new_password.getText();
        String confirmPassword = confirm_password.getText();

    }
    public void overwriteUsername(String newUsername, String oldUsername) throws IOException {
        Methods.setText(error_username,"Username successfully changed!!");
    }
}