package com.expensive_pig.carin.evaluator;

public class IfStatement implements Statement {
    private Statement expression, statementIfTrue, statementIfFalse; // for if else

    public IfStatement(Statement expression, Statement statementIfTrue, Statement statementIfFalse) {
        this.statementIfTrue = statementIfTrue;
        this.expression = expression;
        this.statementIfFalse = statementIfFalse;
    }

    public Statement getExpression() {
        return expression;
    }

    public Statement getStatementIfTrue() {
        return statementIfTrue;
    }

    public Statement getStatementIfFalse() {
        return statementIfFalse;
    }

    @Override
    public String string_val() throws SyntaxError {
        return null;
    }

}
