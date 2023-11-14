package com.example.ooptradingproject.controllers;

import com.example.ooptradingproject.models.MutualFund;
import com.example.ooptradingproject.simulator.ShareMarketAccountSimulator;
import com.example.ooptradingproject.screens.DayTradingEnterInputScreen;
import com.example.ooptradingproject.screens.FillMutualFundPreferencesScreen;
import com.example.ooptradingproject.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HomeController {

    @FXML
    void onSIPReturnsClick(ActionEvent event) {
        MutualFund.calculateSIPReturns(event);
    }

    @FXML
    void onDayTradingBtnClick(ActionEvent event) {
        ShareMarketAccountSimulator simulator = new ShareMarketAccountSimulator();
        DayTradingEnterInputScreen.display(SceneSwitcher.getStage(event), simulator);
    }

    @FXML
    void onMutualFundBtnClick(ActionEvent event) {
        FillMutualFundPreferencesScreen.displayFillMFPrefsScreen(SceneSwitcher.getStage(event));
    }

}
