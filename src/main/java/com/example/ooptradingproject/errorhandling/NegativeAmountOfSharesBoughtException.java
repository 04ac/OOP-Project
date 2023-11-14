package com.example.ooptradingproject.errorhandling;

public class NegativeAmountOfSharesBoughtException extends Exception {
    public NegativeAmountOfSharesBoughtException() {
        super("Error: Can't buy a negative amount of shares.");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
