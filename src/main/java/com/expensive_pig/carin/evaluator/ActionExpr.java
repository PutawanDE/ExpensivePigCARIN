package com.expensive_pig.carin.evaluator;

public class ActionExpr implements Statement {

    private String action;
    private  Statement direction; //for  action


    public ActionExpr(String _action,Statement  _direction) {
        this.action = _action;
        this.direction = _direction;
    }


    @Override
    public String string_val() throws SyntaxError {
        return direction.toString();
    }

    @Override
    public Integer int_val() throws SyntaxError {
        return null;
    }


}
