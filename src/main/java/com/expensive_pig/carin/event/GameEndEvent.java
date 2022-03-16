package com.expensive_pig.carin.event;

import lombok.Getter;

@Getter
public class GameEndEvent extends OutputEvent {
    private String[] status = new String[1];

    public GameEndEvent(String status) {
        super("gameover");
        this.status[0] = status;
    }
}
