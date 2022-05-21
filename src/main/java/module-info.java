module com.example.flowershopproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.flowershopproject to javafx.fxml;
    exports com.example.flowershopproject;
}