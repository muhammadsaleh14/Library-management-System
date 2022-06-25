package com.example.librarymanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class PendingUsers implements Initializable {
    @FXML
    private TableView<Users> table;
    @FXML
    private TableColumn<Users, String> name;
    @FXML
    private TableColumn<Users, String> contact;
    @FXML
    private Label error;

    ObservableList<Users> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "admin");
            PreparedStatement insert = connection.prepareStatement("SELECT * FROM request_user");
            ResultSet resultSet = insert.executeQuery();
            while (resultSet.next()) {
                list.add(new Users(resultSet.getString("username"),resultSet.getString("password"), resultSet.getString("contact")));
            }
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
            table.setItems(list);

            try {connection.close();} catch (SQLException e) {e.printStackTrace();};
            try {insert.close();} catch (SQLException e) {e.printStackTrace();};
            try {resultSet.close();} catch (SQLException e) {e.printStackTrace();};
        } catch (Exception e) {e.printStackTrace();}
    }

    public void Delete(ActionEvent event){
        Object selectedItem = table.getSelectionModel().getSelectedItem();
        Users user = table.getSelectionModel().getSelectedItem();
        table.getItems().remove(selectedItem);
        String name = user.getName();
        if (DBUtil.deleteRequest(name)){}
        else{
            Methods.setText(error,"Select an option");}
    }

    public void addUser(ActionEvent event){
        Object selectedItem = table.getSelectionModel().getSelectedItem();
        Users user = table.getSelectionModel().getSelectedItem();
        table.getItems().remove(selectedItem);
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String currentTime = sdf.format(dt);
        try {
            if(DBUtil.acceptRequest(user) && DBUtil.addDate(user.getName(),currentTime)){}
            else{
                Methods.setText(error,"Error occured");}
        } catch (SQLException e) {Methods.setText(error,"Select a row");e.printStackTrace();}


    }
    public void back(ActionEvent event) throws SQLException, IOException {
        new Methods().switchScenes(event,"librarian.fxml");
    }



}
