package com.expensive_pig.carin.event;

import com.expensive_pig.carin.core.Direction;
import lombok.Getter;

@Getter
public class ShootEvent extends OutputEvent {
    private int[] pos = new int[2];
    private Direction direction;

    public ShootEvent(int posX, int posY, Direction direction) {
        super("shoot");
        pos[0] = posX;
        pos[1] = posY;
        this.direction = direction;
    }
}
