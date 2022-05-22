package com.example.flowershopproject.Controller;

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
import com.example.flowershopproject.Actions.Flower;
import com.example.flowershopproject.Actions.User;
import com.example.flowershopproject.Services.FlowerService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditFlowerController implements Initializable {
    @FXML
    public AnchorPane rootPane2;
    @FXML
    public Label userLabel;
    @FXML
    public Text editMessage;
    @FXML
    public Button cancelButton;
    @FXML
    public Button saveChanges;
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
    public Button changeImgButton;


    public static User getFlorist() {
        return florist;
    }

    private static User florist;
    private static Flower flower;
    private String path;

    public EditFlowerController()
    {

    }

    public void initData(User wr, Flower p) throws Exception{
        this.florist = wr;
        this.userLabel.setText("Hello, " + florist.getUsername() + "!");
        this.flower = p;
        this.quantityField.setText(flower.getStringQuantity());
        this.numProd.setText(flower.getStringNoOfItems());
        this.priceField.setText(flower.getStringPrice());
        this.flowerNameField.setText(flower.getFlowerName());
        flowerImage.setImage(new Image(flower.getImageUrl()));
        setPath("def_pic.jpg"); ///???

    }
    public void handleChangeImgButton()
    {
        Stage stage = (Stage) changeImgButton.getScene().getWindow();
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
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public void handleCancelAction() throws IOException, Exception {
        User us=EditFlowerController.getFlorist();
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
    public void handleSaveChangesAction(){
        if (fieldsReadyToSubmit()) {

            int quant = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());
            int number = Integer.parseInt(numProd.getText());
            String imageUrl = getPath();
            String name = flowerNameField.getText();
            FlowerService.editFlower(flower,name,imageUrl,quant,number,price);
            this.editMessage.setText("Changes has been successfully saved!");


        }
    }
    private boolean fieldsReadyToSubmit()
    {
        if (flowerNameField.getText() == null || flowerNameField.getText().trim().isEmpty() || quantityField.getText()==null || quantityField.getText().trim().isEmpty() || priceField.getText() == null || priceField.getText().trim().isEmpty())
            return false;
        return true;
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
