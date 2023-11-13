package com.example.ooptradingproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SIPReturnsCalculatorScreen extends Application {
    public static double monthlyReturns;
    public static double totalReturns;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        FlowPane fp = new FlowPane(20, 20);
        Scene scene = new Scene(fp, 720, 480);
        stage.setTitle("Trading Simulator");

        Region p1 = new Region();
        p1.setPrefSize(Double.MAX_VALUE, 0.0);
        Region p2 = new Region();
        p2.setPrefSize(Double.MAX_VALUE, 0.0);
        Region p3 = new Region();
        p3.setPrefSize(Double.MAX_VALUE, 0.0);
        Region p4 = new Region();
        p4.setPrefSize(Double.MAX_VALUE, 0.0);

        Label dayTradingResultsLbl = new Label("SIP Returns Calculator!");
        dayTradingResultsLbl.setAlignment(Pos.TOP_LEFT);
        dayTradingResultsLbl.setTextFill(Color.PALEVIOLETRED);
        dayTradingResultsLbl.setFont(new Font(40));

        Label l1 = new Label("Enter the monthly investment (in Rs):");
        l1.setTextFill(Color.GREEN);
        l1.setFont(new Font(20));
        TextField monthlyAmtTextField = new TextField();
        Label l2 = new Label("Enter the investment duration (in months):");
        l2.setTextFill(Color.CORAL);
        l2.setFont(new Font(20));
        TextField durationTextField = new TextField();
        Label l3 = new Label("Enter the expected annual returns % :");
        l3.setTextFill(Color.RED);
        l3.setFont(new Font(20));

        TextField returnsTextField = new TextField();

        Button calcRetBtn = new Button("Calculate Returns");

        Label monthlyReturnsLbl = new Label();
        monthlyReturnsLbl.setTextFill(Color.CADETBLUE);
        monthlyReturnsLbl.setFont(new Font(20));
        Label totalReturnsLbl = new Label();
        totalReturnsLbl.setTextFill(Color.GREEN);
        totalReturnsLbl.setFont(new Font(25));

        fp.getChildren().addAll(dayTradingResultsLbl, l1, monthlyAmtTextField, p1, l2, durationTextField, p2, l3,
                returnsTextField, p3, calcRetBtn, monthlyReturnsLbl, p4, totalReturnsLbl);
        fp.setAlignment(Pos.TOP_CENTER);

        calcRetBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MutualFund.calcSIPReturns(Double.parseDouble(monthlyAmtTextField.getText()),
                        Double.parseDouble(durationTextField.getText()),
                        Double.parseDouble(returnsTextField.getText()));

                monthlyReturnsLbl.setText(String.format("Estimated Monthly returns: %.2f", monthlyReturns));
                totalReturnsLbl.setText(String.format("Estimated total returns over a period of %d months:  Rs. %.2f",
                        Integer.parseInt(durationTextField.getText()), totalReturns));
            }
        });

        stage.setScene(scene);
        stage.show();
    }
}
