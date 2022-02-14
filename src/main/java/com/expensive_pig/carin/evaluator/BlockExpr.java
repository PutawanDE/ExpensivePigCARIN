package com.expensive_pig.carin.evaluator;

import java.util.LinkedList;

public class BlockExpr implements Statement {

    private LinkedList list; // for block





    public BlockExpr(LinkedList _list) {
        this.list = _list   ;
    }

    public LinkedList list_val() throws SyntaxError {
        return list;
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
