package com.expensive_pig.carin.event;

import lombok.Getter;

@Getter
public class ShootEvent extends OutputEvent {
    private int[] pos;
    private int[] toPos;

    public ShootEvent(int posX, int posY, int toPosX, int toPosY) {
        super("shoot");
        pos[0] = posX;
        pos[1] = posY;
        toPos[0] = toPosX;
        toPos[1] = toPosY;
    }
}
