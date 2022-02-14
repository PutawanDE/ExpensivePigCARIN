package com.expensive_pig.carin.evaluator;

public class SensorExpr implements Statement {
    private String command;
    private String direction; //for  action

    public SensorExpr(String command) {
        this.command = command;
    }

    public SensorExpr(String command, String direction) {
        this.command = command;
        this.direction = direction;
    }

    @Override
    public String string_val() throws SyntaxError {
        return command + " " + direction;
    }

    public String getCommand() {
        return command;
    }

    public String getDirection() {
        return direction;
    }
}
