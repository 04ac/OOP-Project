module com.example.ooptradingproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ooptradingproject to javafx.fxml;
    exports com.example.ooptradingproject;
    exports com.example.ooptradingproject.utils;
    opens com.example.ooptradingproject.utils to javafx.fxml;
    exports com.example.ooptradingproject.errorhandling;
    opens com.example.ooptradingproject.errorhandling to javafx.fxml;
    exports com.example.ooptradingproject.screens;
    opens com.example.ooptradingproject.screens to javafx.fxml;
    exports com.example.ooptradingproject.controllers;
    opens com.example.ooptradingproject.controllers to javafx.fxml;
    exports com.example.ooptradingproject.models;
    opens com.example.ooptradingproject.models to javafx.fxml;
    exports com.example.ooptradingproject.simulator;
    opens com.example.ooptradingproject.simulator to javafx.fxml;
}