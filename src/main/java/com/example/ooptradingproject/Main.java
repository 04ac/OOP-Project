package com.example.ooptradingproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(("Home.fxml")));
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("Trading Simulator");
        stage.setScene(scene);
        stage.show();
    }
}
