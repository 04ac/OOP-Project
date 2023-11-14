package com.example.ooptradingproject.errorhandling;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Insufficient Funds exception: " + super.toString();
    }
}
