package com.example.librarymanagementsystem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Users {
    private String name;
    private String password;
    private String contact;
    String time;
    public Users(String name, String password, String contact) {
        this.name = name;
        this.password = password;
        this.contact = contact;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public String getContact() {
        return contact;
    }


    @FXML
    private Button button;


}
