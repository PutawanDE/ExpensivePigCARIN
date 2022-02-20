package com.expensive_pig.carin.event;

import java.util.Map;

public class InputEvent extends Event {
    private Map<String, Integer> data;

    public String getEventType() {
        return eventType;
    }

    public String getAction() {
        return action;
    }

    public Map<String, Integer> getData() {
        return data;
    }
}
