package com.expensive_pig.carin.event;

import lombok.Getter;

@Getter
public class SpawnEvent extends OutputEvent {
    private int[] pos = new int[2];
    private String type;
    private int hp;

    public SpawnEvent(int posX, int posY, String type , int hp) {
        super("spawn");
        pos[0] = posX;
        pos[1] = posY;
        this.type = type;
        this.hp = hp;
    }
}
