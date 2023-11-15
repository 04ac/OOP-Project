package com.example.ooptradingproject.models;

import com.example.ooptradingproject.models.Investment;

public class Share extends Investment {
    public Share(String name, double price) {
        super(name, price);
    }

    @Override
    public String getInvestmentType() {
        return "Stock";
    }

    @Override
    public String toString() {
        return String.format("Investment type: %s\nName: %s\nPrice: %.2f", getInvestmentType(), getName(), getPrice());
    }
}
