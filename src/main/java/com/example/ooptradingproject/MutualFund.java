package com.example.ooptradingproject;

import java.util.Scanner;

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
        funds = new MutualFund[5];
        funds[0] = new MutualFund("ICICI NASDAQ100", 10000, 2, "High", "Index Fund", "Short");
        funds[1] = new MutualFund("ICICI NIFTY50", 500, 1.5, "Medium", "Index Fund", "Long");
        funds[2] = new MutualFund("Quant SmallCap", 1000, 3, "High", "Active Fund", "Short");
        funds[3] = new MutualFund("SBI Debt Fund", 100, 0.5, "Low", "Debt Fund", "Long");
        funds[4] = new MutualFund("SBI Gold Fund", 1500, 1, "Low", "Gold Fund", "Long");
    }

    public static void recommendFund() {
        MutualFund.initializeFunds();
        Scanner sc = new Scanner(System.in);
        String risk;
        String time;
        System.out.println("Enter Investment Amount:");
        double investmentAmt = sc.nextDouble();

        while (true) {
            System.out.println("Enter Risk Tolerance:");
            System.out.println("1.Low\t2.Medium\t3.High");
            int investmentRisk = sc.nextInt(); //1->Low, 2->Medium, 3->High
            if (investmentRisk == 1) {
                risk = "Low";
                break;
            } else if (investmentRisk == 2) {
                risk = "Medium";
                break;
            } else if (investmentRisk == 3) {
                risk = "High";
                break;
            } else {
                System.out.println("Please enter valid input");
            }
        }

        while (true) {
            System.out.println("Enter Investment Horizon:");
            System.out.println("1.Short Term\t2.Long Term");
            int investmentTime = sc.nextInt(); //1->Short, 2->Long
            if (investmentTime == 1) {
                time = "Short";
                break;
            } else if (investmentTime == 2) {
                time = "Long";
                break;
            } else {
                System.out.println("Please enter valid input");
            }
        }
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
        MutualFundRecommendationScreen.main(new String[0]);

    }

    public static void calculateSIPReturns() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the monthly investment (in Rs):");
        double monthlyAmt = sc.nextDouble();
        System.out.println("Enter the investment duration (in months):");
        double duration = sc.nextDouble();
        System.out.println("Enter the expected annual returns % :");
        double returns = sc.nextDouble();
        double monthlyReturn = returns / (12 * 100);
        double finalReturns = monthlyAmt * (1 + monthlyReturn) * ((Math.pow((1 + monthlyReturn), duration) - 1) / monthlyReturn);
        System.out.printf("Estimated Returns: %.2f", finalReturns);
    }
}
