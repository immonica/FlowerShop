package com.example.flowershopproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.flowershopproject.Services.UserService;

import java.util.Objects;

public class Main extends Application {

    private Stage primaryStage;

    public Main() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        UserService.loadUsersFromFile();
        Parent root = (Parent)FXMLLoader.load(Objects.requireNonNull(this.getClass().getClassLoader().getResource("start.fxml")));
        primaryStage.setTitle("FlowerShop");
        primaryStage.setScene(new Scene(root, 525.0D, 448.0D));
        primaryStage.show();

    }
}

