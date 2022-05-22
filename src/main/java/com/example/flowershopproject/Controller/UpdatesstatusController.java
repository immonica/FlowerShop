package com.example.flowershopproject.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.example.flowershopproject.Actions.User;
import com.example.flowershopproject.Exceptions.CannotFindOrder;
import com.example.flowershopproject.Actions.Order;
import com.example.flowershopproject.Services.OrdersService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdatesstatusController implements Initializable{

    @FXML
    public AnchorPane rootPane2;
    @FXML
    public Button backButton;
    @FXML
    public Button updateButton;
    @FXML
    public Label usernameLabel;
    @FXML
    public Label statusLabel;
    @FXML
    public ChoiceBox statuses;
    private static Order order;
    private static User florists;
    ObservableList<String> stats = FXCollections.observableArrayList("pending", "processed", "delivered");

    public UpdatesstatusController(){

    }
    public static Order getOrder() {
        return order;
    }

    public static void setOrder(Order order) {
        UpdatesstatusController.order = order;
    }

    public static User getFlorists() {
        return florists;
    }
    public static void setFlorists(User florists) {
        UpdatesstatusController.florists = florists;
    }

    public void initData(User br, Order or){
        this.order = or;
        this.florists =br;
        this.statuses.setValue("pending");
        this.statuses.setItems(stats);
    }
    public void handleUpdateAction() throws Exception{
        try {
            String newStatus = (String) this.statuses.getValue();
        //    OrdersService.changeOrderStatus(this.order, newStatus);
            statusLabel.setText("Status was successfully updated");
        }catch (CannotFindOrder var){
            statusLabel.setText("There was an error changing the status");
        }
    }
    public void handleBackAction() throws Exception {
        User us=this.getFlorists();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("floristOrders.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);
        OrdersCustomerController control = loader.getController();
        control.initData(us);
        Stage stage1 = (Stage) backButton.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            OrdersService.loadOrdersFromFile();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
