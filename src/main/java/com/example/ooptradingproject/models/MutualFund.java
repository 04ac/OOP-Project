package com.example.ooptradingproject.models;

import com.example.ooptradingproject.screens.MutualFundRecommendationScreen;
import com.example.ooptradingproject.screens.SIPReturnsCalculatorScreen;
import com.example.ooptradingproject.utils.SceneSwitcher;
import javafx.event.ActionEvent;

public class MutualFund extends Investment {
    private final double averageMonthlyGain;
    private String risk;
    private String fundType;
    private String investmentHorizon;
    public static MutualFund[] funds;

    public MutualFund(String name, double price, double averageMonthlyGain) {
        super(name, price);
        this.averageMonthlyGain = averageMonthlyGain;
    }

    public MutualFund(String name, double price, double averageMonthlyGain, String risk, String Type, String time) {
        super(name, price);
        this.averageMonthlyGain = averageMonthlyGain;
        this.risk = risk;
        this.fundType = Type;
        this.investmentHorizon = time;
    }

    @Override
    public String getInvestmentType() {
        return "Mutual Fund";
    }

    public double getAverageMonthlyGain() {
        return averageMonthlyGain;
    }

    public String getInvestmentHorizon() {
        return investmentHorizon;
    }

    public String getRisk() {
        return risk;
    }

    public String getFundType() {
        return fundType;
    }

    public static void printFund(MutualFund m) {
        System.out.println("Name: \t" + m.getName());
        System.out.println("Fund Type:" + m.getFundType());
        System.out.println("Price: \t" + m.getPrice());
        System.out.println("Average Monthly Gain: " + m.getAverageMonthlyGain() + "%");
        System.out.println("Risk Level:" + m.getRisk());
        System.out.println("Ideal investment Duration:" + m.getInvestmentHorizon());
        System.out.println();
    }

    public static void initializeFunds() {
        funds = new MutualFund[10];
        funds[0]= new MutualFund("ICICI NASDAQ100",1200,2.14,"High","Index Fund","Long");
        funds[1]= new MutualFund("ICICI NIFTY50",132.22,1.52,"Medium","Index Fund","Long");
        funds[2]= new MutualFund("Quant SmallCap",1000,3.2,"High","Active Fund","Short");
        funds[3]= new MutualFund("SBI Debt Fund",100,0.5,"Low","Debt Fund","Long");
        funds[4]= new MutualFund("SBI Gold Fund",1500,0.9,"Low","Gold Fund","Long");
        funds[5]= new MutualFund("Tata Digital Fund",750,1.63,"High","Active Fund","Short");
        funds[6]= new MutualFund("Axis Equity Fund",3500,1,"High","Active Fund","Long");
        funds[7]= new MutualFund("Quant Tax Saver Fund",6500,1.36,"Medium","ELSS Fund","Long");
        funds[8]= new MutualFund("Tata ELSS Fund",3500,1.8,"Medium","ELSS Fund","Short");
        funds[9]= new MutualFund("ICICI Commodities Fund",320.2,1,"Low","Commodities Fund","Long");
    }

    public static void recommendFund(ActionEvent event, double investmentAmt, String risk, String time) {
        MutualFund.initializeFunds();
        int isRecommendedFlag = 0;
        for (MutualFund m : MutualFund.funds) {
            if (m.getPrice() > investmentAmt) {
                continue;
            } else if (!(risk.equals(m.getRisk()))) {
                continue;
            } else if (!(time.equals(m.getInvestmentHorizon()))) {
                continue;
            } else {
                isRecommendedFlag = 1;
                MutualFund.printFund(m);
                MutualFundRecommendationScreen.mutualFundObservableList.add(m);
            }
        }
        if (isRecommendedFlag == 0) {
            System.out.println("No funds found at the moment. Please Try again with different inputs...");
        }
        MutualFundRecommendationScreen.displayScene(SceneSwitcher.getStage(event));
}

    public static void calcSIPReturns(double monthlyAmt, double duration, double returns) {
        double monthlyReturn = returns / (12 * 100);
        double finalReturns = monthlyAmt * (1 + monthlyReturn) * ((Math.pow((1 + monthlyReturn), duration) - 1) / monthlyReturn);
        System.out.printf("Estimated Monthly Returns: %.2f", monthlyReturn);
        System.out.printf("Estimated Total Returns: %.2f", finalReturns);

        SIPReturnsCalculatorScreen.monthlyReturns = monthlyReturn;
        SIPReturnsCalculatorScreen.totalReturns = finalReturns;
    }

    public static void calculateSIPReturns(ActionEvent event) {
        SIPReturnsCalculatorScreen.displaySIPScreen(SceneSwitcher.getStage(event));
    }
}
