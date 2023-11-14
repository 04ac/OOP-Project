package com.example.ooptradingproject.screens;

import com.example.ooptradingproject.models.Investment;
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

public class DayTradingResultsScreen extends Application {

    public static String totalProfitLoss;

    public static ObservableList<Investment> invList = FXCollections.observableArrayList();
    public static void main(String[] args) {
        launch(args);
    }

    public static void display(Stage stage) {
        FlowPane fp = new FlowPane(20, 20);
        Scene scene = new Scene(fp, 720, 480);
        stage.setTitle("Trading Simulator");

        Label dayTradingResultsLbl = new Label("Day Trading Results");
        dayTradingResultsLbl.setAlignment(Pos.TOP_CENTER);
        dayTradingResultsLbl.setTextFill(Color.PALEVIOLETRED);
        dayTradingResultsLbl.setFont(new Font(40));


        Label totalProfitOrLossLbl = new Label("");
        totalProfitOrLossLbl.setTextFill(Color.PALEVIOLETRED);
        totalProfitOrLossLbl.setFont(new Font(40));

        totalProfitOrLossLbl.setText(totalProfitLoss);

        TableView<Investment> tableView = new TableView<>();
        tableView.setPrefSize(600, 300);

        TableColumn<Investment, String> nameCol =
                new TableColumn<>("Name");
        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        TableColumn<Investment, String> invTypeCol =
                new TableColumn<>("Investment Type");
        invTypeCol.setCellValueFactory(
                new PropertyValueFactory<>("investmentType"));

        TableColumn<Investment, Double> buyingPriceCol =
                new TableColumn<>("Buying Price");
        buyingPriceCol.setCellValueFactory(
                new PropertyValueFactory<>("price"));

        TableColumn<Investment, Integer> qtyOwnedCol =
                new TableColumn<>("Quantity Owned");
        qtyOwnedCol.setCellValueFactory(
                new PropertyValueFactory<>("quantityOwned"));


        TableColumn<Investment, String> sellpriceCol =
                new TableColumn<>("Selling Price");
        sellpriceCol.setCellValueFactory(
                new PropertyValueFactory<>("sellingPrice"));

        TableColumn<Investment, String> sellValueCol =
                new TableColumn<>("Selling Value");
        sellValueCol.setCellValueFactory(
                new PropertyValueFactory<>("sellingValue"));

        tableView.getColumns().addAll(nameCol, invTypeCol, buyingPriceCol, sellpriceCol, qtyOwnedCol, sellValueCol);
        tableView.getItems().addAll(invList);
        tableView.setPlaceholder(
                new Label("No rows to display"));

        fp.setAlignment(Pos.CENTER);
        fp.getChildren().addAll(dayTradingResultsLbl, tableView, totalProfitOrLossLbl);
        stage.setScene(scene);
        stage.show();
    }

    public void start(Stage stage) {
        display(stage);
    }
}
