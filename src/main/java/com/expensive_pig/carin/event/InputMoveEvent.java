package com.expensive_pig.carin.event;

import lombok.Getter;

@Getter
public class InputMoveEvent extends InputEvent {
    private int[] pos = new int[2];;
    private int[] toPos = new int[2];;

    public InputMoveEvent() {
        super("move");
    }

    public int getPosX() {
        return pos[0];
    }

    public int getPosY() {
        return pos[1];
    }

    public int getToPosX() {
        return toPos[0];
    }

    public int getToPosY() {
        return toPos[1];
    }
}
