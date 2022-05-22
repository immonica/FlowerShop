package com.example.flowershopproject;

import com.example.flowershopproject.Services.UserService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    public Main() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        UserService.loadUsersFromFile();
        Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getClassLoader().getResource("start.fxml")));
        primaryStage.setTitle("FlowerShop");
        primaryStage.setScene(new Scene(root, 450, 370));
        primaryStage.show();

   }
}

