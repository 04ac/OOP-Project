package com.example.ooptradingproject;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MutualFundRecommendationScreen extends Application {

    public static ObservableList<MutualFund> mutualFundObservableList = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FlowPane fp = new FlowPane(20, 20);
        Scene scene = new Scene(fp, 720, 480);
        stage.setTitle("Trading Simulator");

        Label dayTradingResultsLbl = new Label("Mutual Fund Recommendations!");
        dayTradingResultsLbl.setAlignment(Pos.TOP_CENTER);
        dayTradingResultsLbl.setTextFill(Color.PALEVIOLETRED);
        dayTradingResultsLbl.setFont(new Font(40));


        TableView<MutualFund> tableView = new TableView<>();
        tableView.setPrefSize(600, 300);

        TableColumn<MutualFund, String> nameCol =
                new TableColumn<>("Name");
        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        TableColumn<MutualFund, Double> fundTypeCol =
                new TableColumn<>("Fund Type");
        fundTypeCol.setCellValueFactory(
                new PropertyValueFactory<>("fundType"));

        TableColumn<MutualFund, Integer> priceCol =
                new TableColumn<>("Price");
        priceCol.setCellValueFactory(
                new PropertyValueFactory<>("price"));


        TableColumn<MutualFund, String> averageMonthlyGainCol =
                new TableColumn<>("Average Monthly Gain %");
        averageMonthlyGainCol.setCellValueFactory(
                new PropertyValueFactory<>("averageMonthlyGain"));

        TableColumn<MutualFund, String> riskLvlCol =
                new TableColumn<>("Risk Level");
        riskLvlCol.setCellValueFactory(
                new PropertyValueFactory<>("risk"));

        TableColumn<MutualFund, String> idealInvestmentDurationCol =
                new TableColumn<>("Ideal Investment Duration");
        idealInvestmentDurationCol.setCellValueFactory(
                new PropertyValueFactory<>("investmentHorizon"));

        tableView.getColumns().addAll(nameCol, fundTypeCol, priceCol, riskLvlCol, averageMonthlyGainCol, idealInvestmentDurationCol);
        tableView.getItems().addAll(mutualFundObservableList);
        tableView.setPlaceholder(
                new Label("No rows to display"));

        fp.setAlignment(Pos.CENTER);
        fp.getChildren().addAll(dayTradingResultsLbl, tableView);
        stage.setScene(scene);
        stage.show();
    }
}
