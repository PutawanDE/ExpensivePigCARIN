package com.expensive_pig.carin.event;

import lombok.Getter;

@Getter
public abstract class OutputEvent extends Event {
    private String action;

    public OutputEvent(String action) {
        super("output");
        this.action = action;
    }
}
