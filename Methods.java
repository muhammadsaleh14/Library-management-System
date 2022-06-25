package com.example.librarymanagementsystem;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;

public class Methods {
    public static void setText(Label label, String text){
        label.setText(text);
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> label.setText(null));
        pause.play();
    }

    public void switchScenes(ActionEvent event, String fxml) throws IOException{
        System.out.println("point 1");
        //Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        FXMLLoader loader = new FXMLLoader(Methods.class.getResource(fxml));
        System.out.println("point 2");
        Parent root = loader.load();
        System.out.println("point 3");
        Scene scene = new Scene(root);
        System.out.println("point 4");
        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        System.out.println("point 5");
        primaryStage.setScene(scene);
    }
}
