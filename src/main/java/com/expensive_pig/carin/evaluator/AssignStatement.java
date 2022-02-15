package com.expensive_pig.carin.evaluator;

public class AssignStatement implements Statement {
    private Statement identifier;
    private String op;
    private Statement expression;

    public AssignStatement(Statement _identifier, String _op, Statement _expression) {
        this.identifier = _identifier;
        this.op = _op;
        this.expression = _expression;
    }

    @Override
    public String string_val() throws SyntaxError {
        return null;
    }

    public Statement getIdentifier() {
        return identifier;
    }

    public Statement getExpression() {
        return expression;
    }
}
