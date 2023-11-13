module com.example.ooptradingproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ooptradingproject to javafx.fxml;
    exports com.example.ooptradingproject;
}