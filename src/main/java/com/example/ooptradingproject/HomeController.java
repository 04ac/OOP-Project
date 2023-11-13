package com.example.ooptradingproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class HomeController {

    @FXML
    void onSIPReturnsClick(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event,"SIPScreen");
    }

    @FXML
    void onDayTradingBtnClick(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "DayTradingScreen");
    }

    @FXML
    void onMutualFundBtnClick(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "MutualFundScreen");
    }

}
