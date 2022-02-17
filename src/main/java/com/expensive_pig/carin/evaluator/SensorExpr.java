package com.expensive_pig.carin.evaluator;

import com.expensive_pig.carin.core.Direction;

public class SensorExpr implements Statement {
    private String command;
    private Direction direction; //for  action

    public SensorExpr(String command) {
        this.command = command;
    }

    public SensorExpr(String command, Direction direction) {
        this.command = command;
        this.direction = direction;
    }

    @Override
    public String string_val() throws SyntaxError {
        if (direction == null) {
            return command;
        } else {
            return command + " " + direction;
        }
    }

    public String getCommand() {
        return command;
    }

    public Direction getDirection() {
        return direction;
    }
}
