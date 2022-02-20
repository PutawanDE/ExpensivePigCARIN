package com.expensive_pig.carin.event;

import lombok.Getter;

@Getter
public class MoveEvent extends InputEvent {
    private int[] oldPos;
    private int[] newPos;

    public MoveEvent() {
        super("move");
    }

    public int getOldPosX() {
        return oldPos[0];
    }

    public int getOldPosY() {
        return oldPos[1];
    }

    public int getNewPosX() {
        return newPos[0];
    }

    public int getNewPosY() {
        return newPos[1];
    }
}
