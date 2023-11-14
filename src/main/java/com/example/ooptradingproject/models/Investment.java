package com.example.ooptradingproject.models;

public abstract class Investment {
    public String name;
    public double price;
    public String sellingPrice;
    public String sellingValue;
    public int quantityOwned;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityOwned() {
        return quantityOwned;
    }

    public void setQuantityOwned(int quantityOwned) {
        this.quantityOwned = quantityOwned;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public String getSellingValue() {
        return sellingValue;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public void setSellingValue(String sellingValue) {
        this.sellingValue = sellingValue;
    }

    public Investment(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Abstract method to get the type of investment
    public abstract String getInvestmentType();

    // Getter methods
    public String getName() {
        return name;
    }
    public String getFormattedPrice() {
        return String.format("%.2f", price);
    }
}
