package com.example.ooptradingproject.screens;

import com.example.ooptradingproject.models.Investment;
import com.example.ooptradingproject.simulator.ShareMarketAccountSimulator;
import com.example.ooptradingproject.utils.Spacer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import static com.example.ooptradingproject.utils.Constants.MAX_FUNDS;

public class DayTradingEnterInputScreen extends Application {

    public static ObservableList<Investment> invList = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    public static void display(Stage stage, ShareMarketAccountSimulator simulator) {
        FlowPane fp = new FlowPane(20, 20);
        Scene scene = new Scene(fp, 720, 480);
        stage.setTitle("Trading Simulator");

        Label dayTradingLbl = new Label("Day Trading");
        dayTradingLbl.setFont(new Font(40));
        dayTradingLbl.setAlignment(Pos.TOP_CENTER);
        dayTradingLbl.setTextFill(Color.PALEVIOLETRED);

        Label funds = new Label("Max. amount available for investment: Rs. " + MAX_FUNDS);
        funds.setFont(new Font(20));
        funds.setAlignment(Pos.TOP_CENTER);
        funds.setTextFill(Color.BLUEVIOLET);

        TableView<Investment> tableView = new TableView<>();
        tableView.setEditable(true);
        tableView.setPrefSize(360, 200);

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
                new TableColumn<>("Quantity");
        qtyOwnedCol.setCellValueFactory(
                new PropertyValueFactory<>("quantityOwned"));
        qtyOwnedCol.setEditable(true);
        qtyOwnedCol.setCellFactory(TextFieldTableCell.<Investment, Integer>forTableColumn(new IntegerStringConverter()));
        qtyOwnedCol.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow())
                        .setQuantityOwned(e.getNewValue()));

        Label l2 = new Label("Edit the amount of each stock to buy in the table.");
        l2.setTextFill(Color.GREEN);
        l2.setFont(new Font(20));

        Button submitQtyBtn = new Button("Submit");
        submitQtyBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                simulator.runSimulation(event);
            }
        });

        tableView.getColumns().addAll(nameCol, invTypeCol, buyingPriceCol,  qtyOwnedCol);
        tableView.getItems().addAll(invList);
        tableView.setPlaceholder(
                new Label("No rows to display"));

        fp.setAlignment(Pos.CENTER);
        fp.getChildren().addAll(dayTradingLbl, Spacer.getSpacer(), funds, tableView, l2, Spacer.getSpacer(), submitQtyBtn);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage stage) {
        display(stage, new ShareMarketAccountSimulator());
    }
}
