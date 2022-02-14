package com.expensive_pig.carin.evaluator;

public class SyntaxError extends Exception {
    public SyntaxError() {
    }

    public SyntaxError(String errorMessage) {
        super(errorMessage);
    }
}