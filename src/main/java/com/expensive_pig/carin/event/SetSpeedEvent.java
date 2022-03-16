package com.expensive_pig.carin.event;

import lombok.Getter;

@Getter
public class SetSpeedEvent extends InputEvent {
    private float speed;

    public SetSpeedEvent() {
        super("speed");
    }
}
