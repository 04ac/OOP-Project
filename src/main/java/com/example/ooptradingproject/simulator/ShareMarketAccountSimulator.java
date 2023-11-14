package com.example.ooptradingproject.simulator;

import com.example.ooptradingproject.errorhandling.InsufficientFundsException;
import com.example.ooptradingproject.errorhandling.NegativeAmountOfSharesBoughtException;
import com.example.ooptradingproject.models.Investment;
import com.example.ooptradingproject.models.MutualFund;
import com.example.ooptradingproject.models.Share;
import com.example.ooptradingproject.screens.DayTradingEnterInputScreen;
import com.example.ooptradingproject.screens.DayTradingResultsScreen;
import com.example.ooptradingproject.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.example.ooptradingproject.utils.Constants.MAX_FUNDS;

public class ShareMarketAccountSimulator {
    private final ArrayList<Investment> investments;
    private final Map<Investment, Integer> userPortfolio;
    double userBudget;

    public ShareMarketAccountSimulator() {
        investments = new ArrayList<>();
        userPortfolio = new HashMap<>();
        userBudget = MAX_FUNDS;
        initializeInvestments();
    }

    private void initializeInvestments() {
        investments.add(new Share("Share1", 10.0));
        investments.add(new Share("Share2", 20.0));
        investments.add(new Share("Share3", 30.0));

        investments.add(new MutualFund("MutualFund1", 100.0, 5.0));
        investments.add(new MutualFund("MutualFund2", 150.0, 7.5));
        DayTradingEnterInputScreen.invList.addAll(investments);
    }

    private void buyInvestments() throws InsufficientFundsException, NegativeAmountOfSharesBoughtException {

        for (Investment i : DayTradingEnterInputScreen.invList) {

            int quantity = i.getQuantityOwned();
            double cost = quantity * i.getPrice();

            if (cost > userBudget) {
                throw new InsufficientFundsException("Insufficient budget to buy " + quantity + " units of " + i.getName());
            } else if (quantity < 0) {
                throw new NegativeAmountOfSharesBoughtException();
            } else {
                userPortfolio.put(i, quantity);
                userBudget -= cost;
            }

        }
    }

    private void simulateRound() {
        Random random = new Random();
        for (Investment i : investments) {
            double percentageChange = (random.nextDouble() - 0.5) * 0.2; // -10% to +10%
            i.setSellingPrice(String.format("%.2f", (1 + percentageChange) * i.price));
        }
    }

    private void sellInvestments(ActionEvent event) {
        double totalSellValue = 0;

        System.out.println("\nInvestments and their selling prices at closing time:");
        for (Investment i : investments) {
            int quantityOwned = userPortfolio.getOrDefault(i, 0);
            String sellingPrice = i.getSellingPrice();
            double sellingValue = quantityOwned * Double.parseDouble(sellingPrice);
            totalSellValue = totalSellValue + sellingValue;

            i.setSellingValue(String.format("%.2f", sellingValue));
            DayTradingResultsScreen.invList.add(i);
        }

        userBudget = userBudget + totalSellValue;  // Update user budget with total sell value
        System.out.printf("Capital Left: %.2f\n", userBudget);
        double totalProfitLoss = userBudget - MAX_FUNDS;
        if (totalProfitLoss >= 0) {
            DayTradingResultsScreen.totalProfitLoss = String.format("Total Profit: %.2f", totalProfitLoss);
        } else {
            DayTradingResultsScreen.totalProfitLoss = String.format("Total Loss: %.2f", totalProfitLoss);
        }
        DayTradingResultsScreen.display(SceneSwitcher.getStage(event));
    }

    public void runSimulation(ActionEvent event) {
        try {
            buyInvestments();
            simulateRound();
            sellInvestments(event);
        } catch (InsufficientFundsException | NegativeAmountOfSharesBoughtException e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            alert.showAndWait();
            SceneSwitcher.getStage(event).close();
            investments.clear();
            DayTradingEnterInputScreen.invList.clear();
            DayTradingEnterInputScreen.display(stage, new ShareMarketAccountSimulator());
        }
    }
}
