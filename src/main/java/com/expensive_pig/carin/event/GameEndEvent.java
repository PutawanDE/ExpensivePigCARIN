package com.expensive_pig.carin.event;

import lombok.Getter;

@Getter
public class GameEndEvent extends OutputEvent {
    private String status;
    private int virusDeadCount;
    private int antiDeadCount;
    private int timeUnitPlayed;

    public GameEndEvent(String status, int virusDeadCount, int antiDeadCount, int timeUnitPlayed) {
        super("gameover");
        this.status = status;
        this.virusDeadCount = virusDeadCount;
        this.antiDeadCount = antiDeadCount;
        this.timeUnitPlayed = timeUnitPlayed;
    }
}
