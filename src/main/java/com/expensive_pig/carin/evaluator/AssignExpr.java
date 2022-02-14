package com.expensive_pig.carin.evaluator;

public class AssignExpr implements Statement {
    private Statement identifier;
    private String op;
    private Statement expression;
    public AssignExpr(Statement _identifier, String _op, Statement _expression) {
        this.identifier = _identifier;
        this.op = op;
        this.expression = _expression;
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
