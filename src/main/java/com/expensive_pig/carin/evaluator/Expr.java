package com.expensive_pig.carin.evaluator;

public class Expr implements Statement {
    private Statement left, right;
    private String op;

    public Expr(Statement left, String op, Statement right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    public Statement getLeft() {
        return left;
    }

    public Statement getRight() {
        return right;
    }

    public String getOp() {
        return op;
    }

    @Override
    public String string_val() throws SyntaxError {
        return op;
    }
}