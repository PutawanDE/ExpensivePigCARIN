package com.expensive_pig.carin.event;

import lombok.Getter;

@Getter
public class GameOverEvent extends OutputEvent {
    private String[] status = new String[1];

    public GameOverEvent() {
        super("GameOver");
        status[0] = "กระจอก";
    }
}
