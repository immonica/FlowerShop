package com.example.flowershopproject.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.example.flowershopproject.Actions.Flower;
import javafx.scene.control.Button;
import com.example.flowershopproject.Actions.User;
import com.example.flowershopproject.Services.FlowerService;

public class DeleteFlowerController {
    @FXML
    public AnchorPane root;
    @FXML
    public Text text;

    @FXML
    public Button yesButton;
    @FXML
    public Button back;
    @FXML
    public Button noButton;

    public DeleteFlowerController(){

    }
    private static User florist;
    private static Flower flower;

    public static User getFlorist() {
        return florist;
    }

    public void initData(User us, Flower p)
    {
        this.florist = us;
        this.text.setText("Hello, " + florist.getUsername() + "!");
        this.flower = p;
    }

    public void handleYesButton() throws Exception {
        FlowerService.deleteFlower(flower);

    }
    public void handleNoButton() throws Exception {
        User us= DeleteFlowerController.getFlorist();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("flower.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);
        FlowersController control = loader.getController();
        control.initData(us);
        Stage stage1 = (Stage) noButton.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();
    }
    public void handleBackButton() throws Exception {
        User us= DeleteFlowerController.getFlorist();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("flower.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);
        FlowersController control = loader.getController();
        control.initData(us);
        Stage stage1 = (Stage) back.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();
    }


}
