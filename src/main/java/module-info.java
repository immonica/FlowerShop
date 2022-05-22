module FlowerShop {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.io;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;

    opens com.example.flowershopproject to javafx.fxml;
    opens com.example.flowershopproject.Controller to javafx.fxml;
    exports com.example.flowershopproject;
    exports com.example.flowershopproject.Actions;
    exports com.example.flowershopproject.Controller;

}