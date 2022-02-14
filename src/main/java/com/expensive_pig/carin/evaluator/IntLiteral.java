package com.expensive_pig.carin.evaluator;

public class IntLiteral implements Statement {
    private int val;

    public IntLiteral(int val) {
        this.val = val;
    }

    @Override
    public String string_val() throws SyntaxError {
        return Integer.toString(val);
    }

}
