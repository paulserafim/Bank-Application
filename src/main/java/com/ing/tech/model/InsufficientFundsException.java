package com.ing.tech.model;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String s) {
        super(s);
    }
}