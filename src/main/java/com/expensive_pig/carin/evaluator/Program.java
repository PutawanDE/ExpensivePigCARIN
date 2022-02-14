package com.expensive_pig.carin.evaluator;

import java.util.Iterator;
import java.util.LinkedList;

public class Program {
    private final LinkedList<Statement> statementList = new LinkedList<>();
    private Iterator<Statement> iterator = statementList.iterator();

    protected void addStatement(Statement statement) {
        statementList.add(statement);
    }

    public Statement getNextStatement() {
        return hasNext() ? iterator.next() : null;
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public void resetIterator() {
        iterator = statementList.iterator();
    }
}
