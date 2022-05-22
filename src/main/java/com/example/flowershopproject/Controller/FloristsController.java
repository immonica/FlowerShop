package com.example.flowershopproject.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.example.flowershopproject.Actions.User;
import com.example.flowershopproject.Services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FloristsController implements Initializable {
    @FXML
    private AnchorPane rootPane;
    @FXML
    public ListView<User> FloristsList;
    @FXML
    public Text userBox;
    @FXML
    private Button BackToClientMain;
    @FXML
    private Button Select;

    private static List<User> florists = new ArrayList<>();
    private static User client;
    private User selectedFlorist;
    public User getSelectedFlorist() {
        return selectedFlorist;
    }


    public void initData(User c) throws Exception {
        this.client = c;
        this.userBox.setText("Hello, " + client.getUsername() + "!");
        updateFloristsList();
    }

    public static User getClient() {
        return client;
    }

    private void updateFloristsList() throws Exception {
        florists.removeAll(florists);
        List<User> b = new ArrayList<>();
        b = UserService.getFlorists();
        florists = b;
        FloristsList.getItems().addAll(florists);
    }
    public void handleBackToClientMainButton() throws Exception {

        User us= FloristsController.getClient();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("clientmain.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);
        ClientmainController control = loader.getController();
        control.initData(us);
        Stage stage1 = (Stage) BackToClientMain.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();
    }
    public void handleSelectButton() throws Exception {

        User us= FloristsController.getClient();
        User br= FloristsList.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("floristFlowers.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);
        FlowershopBouquetsController control = loader.getController();
        control.initData(us,br);
        Stage stage1 = (Stage) Select.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            UserService.loadUsersFromFile();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }

    }
}
