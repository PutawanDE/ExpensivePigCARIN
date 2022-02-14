package com.expensive_pig.carin.evaluator;

public class IfExpr implements Statement {
    private Statement expression, if_statement, else_statement; // for if else



    public IfExpr(Statement expression, Statement if_statement, Statement else_statement) {
        this.if_statement = if_statement;
        this.expression = expression;

        this.else_statement = else_statement;
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
