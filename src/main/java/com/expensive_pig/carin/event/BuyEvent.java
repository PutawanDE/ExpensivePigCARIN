package com.expensive_pig.carin.event;

import lombok.Getter;

@Getter
public class BuyEvent extends InputEvent {
    private int kind;
    private int[] pos;

    public BuyEvent() {
        super("buy");
    }

    public int getPosX() {
        return pos[0];
    }

    public int getPosY() {
        return pos[1];
    }
}
