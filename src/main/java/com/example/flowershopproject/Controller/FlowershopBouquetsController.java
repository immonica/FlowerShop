package com.example.flowershopproject.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.example.flowershopproject.Actions.User;
import com.example.flowershopproject.Exceptions.FlowerNotInStock;
import com.example.flowershopproject.Actions.Flower;
import com.example.flowershopproject.Services.OrdersService;
import com.example.flowershopproject.Services.FlowerService;

import java.util.ArrayList;
import java.util.List;

public class FlowershopBouquetsController {
    @FXML
    private AnchorPane rootPane;
    @FXML
    public ListView<Flower> FlowersList;
    @FXML
    public Text userBox;
    @FXML
    private Button Order;
    @FXML
    private Button BackToFlorists;
    @FXML
    private ImageView SelectedFlowerImage;
    @FXML
    private Text orderMessage;
    private static User client;
    public void initData(User c, User selFlorist) throws Exception {
        this.client = c;
        this.selectedFlorist = selFlorist;
        this.userBox.setText(client.getUsername());
        updateFloristFlowersList();
        OrdersService.loadOrdersFromFile();
    }

    public static User getClient() {
        return client;
    }
    public void handleBackFloristsButton() throws Exception {

        User us= FlowershopBouquetsController.getClient();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("florists.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);
        FloristsController control = loader.getController();
        control.initData(us);
        Stage stage1 = (Stage) BackToFlorists.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();
    }
    public User getSelectedFlorist() {
        return selectedFlorist;
    }

    private User selectedFlorist;

    private static List<Flower> floristFlowers = new ArrayList<>();

    private void updateFloristFlowersList() throws Exception {
        FlowersList.getItems().clear();
        floristFlowers.removeAll(floristFlowers);
        List<Flower> p = new ArrayList<>();
        p = FlowerService.getFlowers(selectedFlorist.getUsername());
        floristFlowers = p;
        FlowersList.getItems().addAll(floristFlowers);
    }
    public Flower getSelectedProd() {
        return selectedProd;
    }

    private Flower selectedProd;

    public void listViewSelectedFlower(){
        selectedProd = FlowersList.getSelectionModel().getSelectedItem();
        updateGUI();
    }
    private void updateGUI()
    {
        SelectedFlowerImage.setImage(new Image(selectedProd.getImageUrl()));
        this.orderMessage.setText("");
    }
    public void handleOrderAction() throws Exception {
        OrdersService.loadOrdersFromFile();
        if (FlowersList.getSelectionModel().getSelectedItem()!=null)
        {
            try {
                FlowerService.updateNumberOfItems(this.getSelectedProd());
                OrdersService.addOrder(this.getClient().getUsername(), this.getSelectedProd());
                this.orderMessage.setText("You successfully ordered the flower");
            } catch (FlowerNotInStock var2){
                this.orderMessage.setText(var2.getMessage());
            }


        }
        updateFloristFlowersList();



    }

}
