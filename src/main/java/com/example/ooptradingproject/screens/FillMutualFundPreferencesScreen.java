package com.example.ooptradingproject.screens;

import com.example.ooptradingproject.models.MutualFund;
import com.example.ooptradingproject.utils.Spacer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static com.example.ooptradingproject.utils.Constants.APP_NAME;

public class FillMutualFundPreferencesScreen extends Application {

    private static String risk;
    private static String time;

    public static void main(String[] args) {
        launch(args);
    }

    public static void displayFillMFPrefsScreen(Stage stage) {
        FlowPane fp = new FlowPane(20, 20);
        Scene scene = new Scene(fp, 720, 480);
        stage.setTitle(APP_NAME);

        Label mutualFundPreferencesLabel = new Label("Your Preferences:");
        mutualFundPreferencesLabel.setAlignment(Pos.TOP_CENTER);
        mutualFundPreferencesLabel.setTextFill(Color.PALEVIOLETRED);
        mutualFundPreferencesLabel.setFont(new Font(40));

        Label l1 = new Label("Enter Investment Amount (in Rs.):");
        l1.setTextFill(Color.GREEN);
        l1.setFont(new Font(20));
        TextField invAmtTf = new TextField();

        Label l2 = new Label("Enter Your Risk Tolerance:");
        l2.setTextFill(Color.ORANGE);
        l2.setFont(new Font(20));
        ComboBox<String> riskToleranceCB = new ComboBox<>();
        riskToleranceCB.getItems().addAll("Low", "Medium", "High");

        riskToleranceCB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int selectedIdx = riskToleranceCB.getSelectionModel().getSelectedIndex();
                if (selectedIdx == 0) {
                    risk = "Low";
                } else if (selectedIdx == 1) {
                    risk = "Medium";
                } else if (selectedIdx == 2) {
                    risk = "High";
                }
            }
        });

        Label l3 = new Label("Enter Investment Horizon:");
        l3.setTextFill(Color.ORANGE);
        l3.setFont(new Font(20));
        ComboBox<String> invHorizonCB = new ComboBox<>();
        invHorizonCB.getItems().addAll("Short Term", "Long Term");

        invHorizonCB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int selectedIdx = invHorizonCB.getSelectionModel().getSelectedIndex();
                if (selectedIdx == 0) {
                    time = "Short";
                } else if (selectedIdx == 1) {
                    time = "Long";
                }
            }
        });

        Button submitMFPrefsBtn = new Button("Get Recommendations!");
        submitMFPrefsBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MutualFund.recommendFund(event, Double.parseDouble(invAmtTf.getText()), risk, time);
            }
        });

        fp.getChildren().addAll(mutualFundPreferencesLabel, Spacer.getSpacer(), l1, invAmtTf, Spacer.getSpacer(),
                l2, riskToleranceCB, Spacer.getSpacer(), l3, invHorizonCB, Spacer.getSpacer(), submitMFPrefsBtn);

        fp.setAlignment(Pos.TOP_CENTER);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage stage) {
        displayFillMFPrefsScreen(stage);
    }
}
