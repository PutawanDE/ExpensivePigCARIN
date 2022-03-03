package com.expensive_pig.carin.event;

import lombok.Getter;

@Getter
public class InfectEvent extends OutputEvent {
    private int[] pos;
    private int kind;

    public InfectEvent(int posX, int posY, int kind) {
        super("infect");
        pos[0] = posX;
        pos[1] = posY;
        this.kind = kind;
    }
}
