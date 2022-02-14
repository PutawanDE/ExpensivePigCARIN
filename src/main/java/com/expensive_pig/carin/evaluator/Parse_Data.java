package com.expensive_pig.carin.evaluator;

public class Parse_Data implements Statement {
    // same like DoubleLit
    private String val;

    public Parse_Data(String val) {
        this.val = val;
    }



    public Integer int_val() throws SyntaxError {
        return  Integer.parseInt(val);
    }

    @Override
    public String string_val() throws SyntaxError {
        return val;
    }




}
