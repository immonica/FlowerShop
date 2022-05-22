package com.example.flowershopproject.Controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.example.flowershopproject.Actions.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FloristmainController implements Initializable{
    @FXML
    public AnchorPane rootPane2;
    @FXML
    public Text userBox;
    @FXML
    private Label usernameLabel;

    @FXML
    public Button logoutButton;

    @FXML
    public Button viewFlowersButton;

    @FXML
    public Button viewOrdersButton;
    private static User florist;
    public FloristmainController()
    {

    }
    public void initData(User wr){
        this.florist = wr;
        this.usernameLabel.setText("Hello, " + florist.getUsername() + "!");
    }

    public static User getFlorist() {
        return florist;
    }

    public static void setFlorist(User florist) {
        FloristmainController.florist = florist;
    }

    public void handleLogOutAction() throws IOException {
        Stage stage = (Stage) rootPane2.getScene().getWindow();
        Stage prevStage = (Stage) logoutButton.getScene().getWindow();
        Parent root = (Parent) FXMLLoader.load(this.getClass().getClassLoader().getResource("start.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void handleViewFlowersAction() throws Exception
    {
        User us= FloristmainController.getFlorist();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("flower.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);
        FlowersController control = loader.getController();
        control.initData(us);
        Stage stage1 = (Stage) viewFlowersButton.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();
    }
    public void handleViewOrdersAction() throws Exception{
        User us= FloristmainController.getFlorist();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("floristOrders.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);
        OrdersCustomerController control = loader.getController();
        control.initData(us);
        Stage stage1 = (Stage) viewFlowersButton.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
