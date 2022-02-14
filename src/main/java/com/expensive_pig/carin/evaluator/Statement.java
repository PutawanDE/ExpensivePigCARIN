package com.expensive_pig.carin.evaluator;

public interface Statement {


        String string_val() throws SyntaxError;

        Integer int_val() throws SyntaxError;
}
