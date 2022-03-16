package com.expensive_pig.carin.event;

import lombok.Getter;

@Getter
public class RestartGameEvent extends OutputEvent {
    private String status;
    private String msg;

    public RestartGameEvent(String status, String msg) {
        super("restart");
        this.msg = msg;
        this.status = status;
    }
}
