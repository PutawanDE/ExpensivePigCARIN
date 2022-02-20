package com.expensive_pig.carin.event;

public class OutputEvent extends Event {
    private String action;

    public OutputEvent(String action) {
        super("output");
        this.action = action;
    }
}
