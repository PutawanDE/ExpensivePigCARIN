package com.expensive_pig.carin.evaluator;

public class Identifier implements Statement {
    // same like DoubleLit
    private String val;

    public Identifier(String val) {
        this.val = val;
    }

    @Override
    public String string_val() throws SyntaxError {
        return val;
    }
}