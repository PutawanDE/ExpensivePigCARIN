package com.expensive_pig.carin.event;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class InputEventQueue {
    private Queue<InputEvent> eventQueue = new ConcurrentLinkedQueue<>();

    public void addEvent(InputEvent e) {
        eventQueue.add(e);
    }

    public InputEvent removeEvent() {
        return eventQueue.poll();
    }

    public InputEvent peekEvent() {
        return eventQueue.peek();
    }

    public boolean isEmpty() {
        return eventQueue.isEmpty();
    }
}
