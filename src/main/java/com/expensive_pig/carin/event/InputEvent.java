package com.expensive_pig.carin.event;

import java.util.Map;

public class InputEvent extends Event {
    private Map<String, Object> data;

    public String getEventType() {
        return eventType;
    }

    public String getAction() {
        return action;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
