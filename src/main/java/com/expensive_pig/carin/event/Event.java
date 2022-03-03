package com.expensive_pig.carin.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public abstract class Event {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String eventType;

    public Event(String eventType) {
        this.eventType = eventType;
    }
}
