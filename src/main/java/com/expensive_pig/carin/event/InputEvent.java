package com.expensive_pig.carin.event;

import lombok.Getter;

@Getter
public abstract class InputEvent extends Event {
    private String action;

    public InputEvent(String action) {
        super("input");
        this.action = action;
    }
}
