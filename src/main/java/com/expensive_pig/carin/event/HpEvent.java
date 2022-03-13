package com.expensive_pig.carin.event;

import lombok.Getter;

@Getter
public class HpEvent extends OutputEvent {
    private int[] pos = new int[2];
    private int change;

    public HpEvent(int posX, int posY, int change) {
        super("hp");
        pos[0] = posX;
        pos[1] = posY;
        this.change = change;
    }
}
