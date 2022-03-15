package com.expensive_pig.carin.event;

import lombok.Getter;

@Getter
public class InfectEvent extends OutputEvent {
    private int[] pos = new int[2];
    private String type;

    public InfectEvent(int posX, int posY, String type) {
        super("infect");
        pos[0] = posX;
        pos[1] = posY;
        this.type = type;
    }
}
