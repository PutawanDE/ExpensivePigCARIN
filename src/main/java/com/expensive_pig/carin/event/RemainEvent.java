package com.expensive_pig.carin.event;

import lombok.Getter;

@Getter
public class RemainEvent extends OutputEvent {
    private int[] remain = new int[2];

    public RemainEvent(int Antiremain, int Virusremain) {
        super("remain");
        remain[0] = Antiremain;
        remain[1] = Virusremain;
    }
}
