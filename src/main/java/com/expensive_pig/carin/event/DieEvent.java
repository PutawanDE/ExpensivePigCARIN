package com.expensive_pig.carin.event;

import lombok.Getter;

@Getter
public class DieEvent extends OutputEvent {
    private int[] pos = new int[2];

    public DieEvent(int posX, int posY) {
        super("die");
        pos[0] = posX;
        pos[1] = posY;
    }
}
