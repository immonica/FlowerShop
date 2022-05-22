package com.example.flowershopproject.Controller;

import com.example.flowershopproject.Actions.User;
import com.example.flowershopproject.Exceptions.FlowerAlreadyExists;
import com.example.flowershopproject.Services.FlowerService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddFlowerController implements Initializable {

    @FXML
    public AnchorPane rootPane2;
    @FXML
    public Label userLabel;
    @FXML
    public Text registrationMessage;
    @FXML
    public Button cancelButton;
    @FXML
    public Button addButton;
    @FXML
    public TextField flowerNameField;
    @FXML
    public TextField quantityField;
    @FXML
    public TextField priceField;
    @FXML
    public TextField numProd;

    @FXML
    public ImageView flowerImage;
    @FXML
    public Button selectImageButton;

    @FXML
    public Text addFlowerMessage;

    @FXML
    public Button resetImageButton;

    private static User florist;
    private String path;

    public AddFlowerController(){

    }

    public void handleResetAction(){
        flowerImage.setImage(new Image("def_pic.jpg")); ///???
        setPath("def_pic.jpg");///???
    }

    public void handleSelectImage(){

        Stage stage = (Stage) selectImageButton.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");

        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg");
        fileChooser.getExtensionFilters().add(imageFilter);
        File imageFile = fileChooser.showOpenDialog(stage);
        if (imageFile.isFile()){
            setPath(imageFile.toURI().toString());
            flowerImage.setImage(new Image(imageFile.toURI().toString()));
        }
    }


    public void handleAddAction(){
        if (fieldsReadyToSubmit()) {
            try {
                int quant = Integer.parseInt(quantityField.getText());
                double price = Double.parseDouble(priceField.getText());
                int number = Integer.parseInt(numProd.getText());
                String imageUrl = getPath();
                FlowerService.addFlower(this.flowerNameField.getText(), imageUrl, quant, price, number);
                this.addFlowerMessage.setText("Flower has been successfully added!");
            } catch (FlowerAlreadyExists var2) {
                this.addFlowerMessage.setText(var2.getMessage());
            }
        }

    }
    private boolean fieldsReadyToSubmit()
    {
        if (flowerNameField.getText() == null ||flowerNameField.getText().trim().isEmpty() || quantityField.getText()==null || quantityField.getText().trim().isEmpty() || priceField.getText() == null || priceField.getText().trim().isEmpty())
            return false;
        return true;
    }

    public void handleCancelAction() throws IOException, Exception {
        User us=AddFlowerController.getFlorist();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("flower.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);
        FlowersController control = loader.getController();
        control.initData(us);
        Stage stage1 = (Stage) cancelButton.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();
    }

    public static User getFlorist() {
        return florist;
    }

    public static void setFlorist(User florist) {
        AddFlowerController.florist = florist;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)

    {

        priceField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.matches("\\d*(\\.\\d*)?")){
                    priceField.setText(oldValue);
                }
            }
        });

        quantityField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.matches("\\d*?")){
                    quantityField.setText(oldValue);
                }
            }
        });

        numProd.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.matches("\\d*?")){
                    numProd.setText(oldValue);
                }
            }
        });
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void initialize(User us) {
        this.setFlorist(us);
        try {
            FlowerService.loadFlowerFromFile();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
        flowerImage.setImage(new Image("def_pic.jpg")); ///??
        setPath("def_pic.jpg");///???
    }

}
