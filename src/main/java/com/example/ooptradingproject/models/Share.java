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
}
