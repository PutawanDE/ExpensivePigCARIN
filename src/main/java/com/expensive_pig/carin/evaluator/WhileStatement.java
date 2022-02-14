package com.expensive_pig.carin.evaluator;

public class WhileStatement implements Statement {
    private Statement expression, statementIfTrue; // for if else

    public WhileStatement(Statement expression, Statement statementIfTrue) {
        this.expression = expression;
        this.statementIfTrue = statementIfTrue;
    }

    @Override
    public String string_val() throws SyntaxError {
        return null;
    }

    public Statement getExpression() {
        return expression;
    }

    public Statement getStatementIfTrue() {
        return statementIfTrue;
    }
}
