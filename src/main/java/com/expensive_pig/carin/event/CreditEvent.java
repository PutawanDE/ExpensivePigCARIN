package com.expensive_pig.carin.event;

import lombok.Getter;

@Getter
public class CreditEvent extends OutputEvent {
    private int[] pos = new int[2];
    private int remain;

    public CreditEvent(int posX, int posY, int remain) {
        super("credit");
        pos[0] = posX;
        pos[1] = posY;
        this.remain = remain;
    }

}
