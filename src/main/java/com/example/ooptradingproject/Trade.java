package com.example.ooptradingproject;

import java.util.*;

class Share extends Investment {
    public Share(String name, double price) {
        super(name, price);
    }
    @Override
    public String getInvestmentType() {
        return "Stock";
    }
}

class MutualFund extends Investment {
    private final double averageMonthlyGain;
    private String risk;
    private String fundType;
    private String investmentHorizon;
    public static MutualFund[] funds;
    public MutualFund(String name, double price, double averageMonthlyGain) {
        super(name, price);
        this.averageMonthlyGain = averageMonthlyGain;
    }
    public MutualFund(String name, double price,double averageMonthlyGain, String risk, String Type, String time) {
        super(name, price);
        this.averageMonthlyGain = averageMonthlyGain;
        this.risk= risk;
        this.fundType= Type;
        this.investmentHorizon= time;
    }
    @Override
    public String getInvestmentType() {
        return "Mutual Fund";
    }
    public double getAverageMonthlyGain() {
        return averageMonthlyGain;
    }
    public String getInvestmentHorizon(){
        return investmentHorizon;
    }
    public String getRisk(){
        return risk;
    }
    public String getFundType(){
        return fundType;
    }

    public static void printFund(MutualFund m){
        System.out.println("Name: \t" +m.getName());
        System.out.println("Fund Type:" +m.getFundType());
        System.out.println("Price: \t" +m.getPrice());
        System.out.println("Average Monthly Gain: " +m.getAverageMonthlyGain()+"%");
        System.out.println("Risk Level:" +m.getRisk());
        System.out.println("Ideal investment Duration:" +m.getInvestmentHorizon());
        System.out.println();
    }
    public static void initializeFunds(){
        funds = new MutualFund[5];
        funds[0]= new MutualFund("ICICI NASDAQ100",10000,2,"High","Index Fund","Short");
        funds[1]= new MutualFund("ICICI NIFTY50",500,1.5,"Medium","Index Fund","Long");
        funds[2]= new MutualFund("Quant SmallCap",1000,3,"High","Active Fund","Short");
        funds[3]= new MutualFund("SBI Debt Fund",100,0.5,"Low","Debt Fund","Long");
        funds[4]= new MutualFund("SBI Gold Fund",1500,1,"Low","Gold Fund","Long");
    }
    public static void recommendFund(){
        MutualFund.initializeFunds();
        Scanner sc = new Scanner(System.in);
        String risk;
        String time;
        System.out.println("Enter Investment Amount:");
        double investmentAmt = sc.nextDouble();

        while(true){
            System.out.println("Enter Risk Tolerance:");
            System.out.println("1.Low\t2.Medium\t3.High");
            int investmentRisk= sc.nextInt(); //1->Low, 2->Medium, 3->High
            if(investmentRisk==1){
                risk = "Low";
                break;
            } else if (investmentRisk==2) {
                risk = "Medium";
                break;
            } else if (investmentRisk==3) {
                risk = "High";
                break;
            } else {
                System.out.println("Please enter valid input");
            }
        }

        while(true){
            System.out.println("Enter Investment Horizon:");
            System.out.println("1.Short Term\t2.Long Term");
            int investmentTime= sc.nextInt(); //1->Short, 2->Long
            if(investmentTime==1){
                time="Short";
                break;
            } else if (investmentTime==2) {
                time="Long";
                break;
            } else {
                System.out.println("Please enter valid input");
            }
        }
        int isRecommendedFlag=0;
        for(MutualFund m : MutualFund.funds){
            if(m.getPrice()>investmentAmt){
                continue;
            } else if (!(risk.equals(m.getRisk()))) {
                continue;
            } else if (!(time.equals(m.getInvestmentHorizon()))) {
                continue;
            }else {
                isRecommendedFlag=1;
                MutualFund.printFund(m);
            }
        }
        if(isRecommendedFlag==0){
            System.out.println("No funds found at the moment. Please Try again with different inputs...");
        }
    }
    public static void calculateSIPReturns(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the monthly investment (in Rs):");
        double monthlyAmt = sc.nextDouble();
        System.out.println("Enter the investment duration (in months):");
        double duration = sc.nextDouble();
        System.out.println("Enter the expected annual returns % :");
        double returns = sc.nextDouble();
        double monthlyReturn = returns/(12*100);
        double finalReturns= monthlyAmt*(1+monthlyReturn)*( (Math.pow((1+monthlyReturn),duration)-1) / monthlyReturn );
        System.out.printf("Estimated Returns: %.2f",finalReturns);
    }
}


class ShareMarketAccountSimulator {
    private ArrayList<Investment> investments;
    private Map<Investment, Integer> userPortfolio;
    double userBudget;

    ShareMarketAccountSimulator() {
        investments = new ArrayList<>();
        userPortfolio = new HashMap<>();
        userBudget = 10000;
        initializeInvestments();
    }

    private void initializeInvestments() {
        investments.add(new Share("Share1", 10.0));
        investments.add(new Share("Share2", 20.0));
        investments.add(new Share("Share3", 30.0));
        investments.add(new Share("Share4", 40.0));
        investments.add(new Share("Share5", 50.0));
        investments.add(new Share("Share6", 60.0));

        investments.add(new MutualFund("MutualFund1", 100.0, 5.0));
        investments.add(new MutualFund("MutualFund2", 150.0, 7.5));
    }

    private void printInvestments() {
        System.out.printf("Capital Available:\tRs%.2f\n",userBudget);
        System.out.println("Investments available Today:");
        for (Investment i : investments) {
            System.out.printf("%s: %s - Rs%.2f\n",i.getInvestmentType() , i.getName() , i.getPrice());
        }
    }

    private void buyInvestments() {
        Scanner scanner = new Scanner(System.in);
        for (Investment i : investments) {
            try {
                System.out.print("Enter the quantity of " + i.getName() + " to buy: ");
                int quantity = scanner.nextInt();
                double cost = quantity * i.getPrice();

                if (cost > userBudget) {
                    throw new InsufficientFundsException("Insufficient budget to buy " + quantity + " units of " + i.getName());
                }
                else {
                    i.setQuantityOwned(quantity);
                    userPortfolio.put(i, quantity);
                    userBudget -= cost;
                }
            } catch (InsufficientFundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void simulateRound() {
        Random random = new Random();
        for (Investment i : investments) {
            double percentageChange = (random.nextDouble() - 0.5) * 0.2; // -10% to +10%
            i.setSellingPrice(String.format("%.2f", (1 + percentageChange)*i.price));
        }
    }

    private void sellInvestments() {
        double totalSellValue = 0;

        System.out.println("\nInvestments and their selling prices at closing time:");
        for (Investment i : investments) {
            int quantityOwned = userPortfolio.getOrDefault(i, 0);
            String sellingPrice = i.getSellingPrice();
            double sellingValue = quantityOwned * Double.parseDouble(sellingPrice);
            totalSellValue = totalSellValue + sellingValue ;
//            String n = i.getInvestmentType() + ": " + i.getName() + " - Quantity: " + quantityOwned + ", Selling Price: Rs" + sellingPrice + "\n";
//            String n = String.format("%s - Quantity: %d, Selling Price: Rs%.2f, Total Amt: Rs%.2f\n"/*,i.getInvestmentType()*/,i.getName(),quantityOwned,sellingPrice,sellingValue);
//            DayTradingResultsScreen.results.add(n);
            i.setSellingValue(String.format("%.2f", sellingValue));
            DayTradingResultsScreen.invList.add(i);
        }

        userBudget = userBudget + totalSellValue;  // Update user budget with total sell value
        System.out.printf("Capital Left: %.2f\n",userBudget);
        double totalProfitLoss = userBudget - 10000;
        if(totalProfitLoss >=0){
            DayTradingResultsScreen.totalProfitLoss = String.format("Total Profit: %.2f",totalProfitLoss);
        }else{
            DayTradingResultsScreen.totalProfitLoss = String.format("Total Loss: %.2f",totalProfitLoss);
        }

        DayTradingResultsScreen.main(new String[0]);
    }
    public void runSimulation() {
        printInvestments();
        buyInvestments();
        simulateRound();
        sellInvestments();
    }
}

public class Trade {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Run Day Trading Simulator");
            System.out.println("2. Get Mutual Fund Recommendations");
            System.out.println("3. SIP Returns Calculator");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    ShareMarketAccountSimulator simulator = new ShareMarketAccountSimulator();
                    simulator.runSimulation();
                    break;
                case 2:
                    MutualFund.recommendFund();
                    break;
                case 3:
                    MutualFund.calculateSIPReturns();
                    break;
                case 4:
                    System.out.println("Exiting");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Insufficient Funds exception: "+ super.toString();
    }
}
