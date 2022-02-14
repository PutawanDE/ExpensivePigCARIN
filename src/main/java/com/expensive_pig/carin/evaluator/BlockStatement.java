package com.expensive_pig.carin.evaluator;

import java.util.LinkedList;

public class BlockStatement implements Statement {

    private LinkedList<Statement> list; // for block

    public BlockStatement(LinkedList<Statement> _list) {
        this.list = _list;
    }

    public LinkedList<Statement> list_val() {
        return list;
    }


    @Override
    public String string_val() throws SyntaxError {
        return null;
    }
}
