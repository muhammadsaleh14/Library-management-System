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
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class LibrarianController implements Initializable {
    @FXML private Button exit;
    @FXML private TableView<Books> table;
    @FXML private TableColumn<Books, Integer> status;
    @FXML private TableColumn<Books, String> title;
    @FXML private TableColumn<Books, String> genre;
    @FXML private TableColumn<Books, String> author;
    @FXML private TableColumn<Books, String> description;
    @FXML private TextField new_username;
    @FXML private Label username_already_exists;
    @FXML private TextField new_password;
    @FXML private TextField confirm_password;
    @FXML private Label error;


    public ObservableList<Books> list = FXCollections.observableArrayList(
        new Books(1, "nava", "sdaasd","ME","nice"),
        new Books(2, "Fahim","fahim","me","nice"),
        new Books(3, "Shariful", "Islam", "me","nice")
    );


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        status.setCellValueFactory(new PropertyValueFactory<Books, Integer>("status"));
        title.setCellValueFactory(new PropertyValueFactory<Books, String>("title"));
        genre.setCellValueFactory(new PropertyValueFactory<Books, String>("genre"));
        author.setCellValueFactory(new PropertyValueFactory<Books, String>("author"));
        description.setCellValueFactory(new PropertyValueFactory<Books, String>("description"));

        table.setItems(list);
    }
    public void exit(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }
    public void editAccount(ActionEvent event) throws IOException, SQLException {
        new Methods().switchScenes(event, "editAccount.fxml");
    }
    public void pendingUsers(ActionEvent event) throws SQLException, IOException {
        new Methods().switchScenes(event,"pending_users.fxml");

    }
}
