package com.expensive_pig.carin.evaluator;

public class IfExpr implements Statement {
    private Statement expression, statement_if_true, statement_if_false; // for if else



    public IfExpr(Statement expression, Statement statement_if_true, Statement statement_if_false) {
        this.statement_if_true = statement_if_true;
        this.expression = expression;

        this.statement_if_false = statement_if_false;
    }



    @Override
    public String string_val() throws SyntaxError {
        return null;
    }

    @Override
    public Integer int_val() throws SyntaxError {
        return null;
    }


}
