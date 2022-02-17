package com.expensive_pig.carin.evaluator;

import com.expensive_pig.carin.core.Direction;

public class ActionCommand implements Statement {
    private String command;
    private Direction direction; //for  action

    public ActionCommand(String _action, Direction _direction) {
        this.command = _action;
        this.direction = _direction;
    }

    @Override
    public String string_val() throws SyntaxError {
        return command + " " + direction;
    }

    public String getActionCmd() {
        return command;
    }

    public String getDirection() {
        return direction.toString();
    }
}
