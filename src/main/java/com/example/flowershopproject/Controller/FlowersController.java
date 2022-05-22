package com.example.flowershopproject.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.example.flowershopproject.Actions.Flower;
import com.example.flowershopproject.Actions.User;
import com.example.flowershopproject.Services.FlowerService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FlowersController implements Initializable{
    @FXML
    public AnchorPane rootPane2;
    @FXML
    public Label userLabel;
    @FXML
    public Text registrationMessage;
    @FXML
    public Button backToMainButton;
    @FXML
    public ListView<Flower> flowersList;
    @FXML
    public Button addFlowerButton;
    @FXML
    public Button editFlowerButton;

    @FXML
    public Button deleteFlowerButton;

    @FXML
    public ImageView selectedFlowerImg;

    private static User florist;

    private static Flower selectedFlower;
    private static List<Flower> flowers = new ArrayList<>();

    private void updateFlowerList() throws Exception {
        flowersList.getItems().clear();
        flowers.removeAll(flowers);
        List<Flower> p = new ArrayList<>();
        p = FlowerService.getFlowers(florist.getUsername());
        flowers = p;
        flowersList.getItems().addAll(flowers);
    }
    public static List<Flower> getFlowers() {
        return flowers;
    }
    public void selectedFlower(Flower flower) throws Exception {
        selectedFlower=flower;
        updateFlowerList();
        updateGUI();

    }

    public void listViewselectedFlower(){
        if (flowersList.getSelectionModel().getSelectedItem()!=null) {
            selectedFlower = flowersList.getSelectionModel().getSelectedItem();
            updateGUI();
        }
    }
    private void updateGUI()
    {
        selectedFlowerImg.setImage(new Image(selectedFlower.getImageUrl()));
    }
    public FlowersController(){

    }
    public void initData(User br) throws Exception{
        this.florist = br;
        this.userLabel.setText("Hello, " + florist.getUsername() + "!");
        updateFlowerList();

    }

    public void handleBackToMain() throws IOException {
        User us=FlowersController.getFlorist();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("floristmain.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);
        FloristmainController control = loader.getController();
        control.initData(us);
        Stage stage1 = (Stage) backToMainButton.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();
    }
    public void handleAddFlowerAction() throws  IOException {
        User us=FlowersController.getFlorist();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("addFlower.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);
        AddFlowerController control = loader.getController();
        control.initialize(us);
        Stage stage1 = (Stage) addFlowerButton.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();
    }
    public void handleEditFlowerAction() throws Exception {
        User us=FlowersController.getFlorist();
        Flower p = FlowersController.getselectedFlower();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("editFlower.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);
        EditFlowerController control = loader.getController();
        control.initData(us,p);
        Stage stage1 = (Stage) editFlowerButton.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();

    }
    public static Flower getselectedFlower() {
        return selectedFlower;
    }

    public void handleDeleteFlowerAction() throws Exception {
        User us=FlowersController.getFlorist();
        Flower p = FlowersController.getselectedFlower();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("deleteFlower.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);
        DeleteFlowerController control = loader.getController();
        control.initData(us,p);
        Stage stage1 = (Stage) deleteFlowerButton.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();

    }
    public static User getFlorist() {
        return florist;
    }

    public static void setFlorist(User florist) {
        FlowersController.florist = florist;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FlowerService.loadFlowerFromFile();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }

    }
}
