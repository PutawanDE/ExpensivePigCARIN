package com.expensive_pig.carin.evaluator;

public class WhileExpr implements Statement {

    private Statement expression, if_statement ; // for if else



    public WhileExpr(Statement expression, Statement if_statement) {
        this.expression = expression;
        this.if_statement = if_statement;


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
