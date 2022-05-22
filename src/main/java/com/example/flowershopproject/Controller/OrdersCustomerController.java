package com.example.flowershopproject.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.example.flowershopproject.Actions.Order;
import com.example.flowershopproject.Actions.User;
import com.example.flowershopproject.Services.OrdersService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrdersCustomerController implements Initializable{
    @FXML
    public AnchorPane rootPane2;
    @FXML
    public Label userLabel;
    @FXML
    public Text registrationMessage;
    @FXML
    public Button backToMainButton;
    @FXML
    public ListView<Order> ordersList;
    @FXML
    public Button updateStatus;
    private static User florist;
    private Order selectedOrder;
    private static List<Order> orders = new ArrayList<>();

    public OrdersCustomerController(){

    }

    public static User getFlorist() {
        return florist;
    }

   // public static void setFlorist(User florist) {
    //    OrdersFloristController.florist = florist;
  //  }
    public void handleBackToMain() throws IOException {
     //   User us=OrdersFloristController.getFlorist();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("floristmain.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);
        FloristmainController control = loader.getController();
     //   control.initData(us);
        Stage stage1 = (Stage) backToMainButton.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();
    }
    public void handleUpdateStatus() throws IOException{
        if (ordersList.getSelectionModel().getSelectedItem()!=null) {
            User us = getFlorist();
            Order or = ordersList.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getClassLoader().getResource("updatestatus.fxml"));
            Parent root1 = loader.load();
            Scene scene1 = new Scene(root1);
            UpdatesstatusController control = loader.getController();
            control.initData(us, or);
            Stage stage1 = (Stage) updateStatus.getScene().getWindow();
            stage1.setScene(scene1);
            stage1.show();
        }
    }
    private void updateOrderList() throws Exception {
        ordersList.getItems().clear();
        orders.removeAll(orders);
        List<Order> o = new ArrayList<>();
     //   o = OrdersService.getBouquetOrders(florist.getUsername());
        orders = o;
        ordersList.getItems().addAll(orders);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            OrdersService.loadOrdersFromFile();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }

    }
    public void initData(User br) throws Exception {
        this.florist = br;
        this.userLabel.setText("Hello, " + florist.getUsername() + "!");
        updateOrderList();
    }
}
