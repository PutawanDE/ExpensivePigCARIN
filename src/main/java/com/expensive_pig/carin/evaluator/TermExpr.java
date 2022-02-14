package com.expensive_pig.carin.evaluator;

public class TermExpr implements Statement {
    private Statement left, right;
    private String op;

    public TermExpr(Statement left, String op, Statement right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }


    @Override
    public String string_val() throws SyntaxError {
        return op;
    }

    @Override
    public Integer int_val() throws SyntaxError {
        Integer lv = left.int_val();
        Integer rv = right.int_val();

        return switch (op) {
            case "+" -> lv + rv;
            case "-" -> lv - rv;
            case "*" -> lv * rv;
            case "/" -> lv / rv;
            case "%" -> lv % rv;
            default -> throw new SyntaxError();
        };
    }





}