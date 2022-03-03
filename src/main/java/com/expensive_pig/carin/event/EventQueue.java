package com.expensive_pig.carin.event;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class EventQueue<E extends Event> {
    private final Queue<E> eventQueue = new ConcurrentLinkedQueue<>();

    public void addEvent(E e) {
        eventQueue.add(e);
    }

    public E removeEvent() {
        return eventQueue.poll();
    }

    public E peekEvent() {
        return eventQueue.peek();
    }

    public boolean isEmpty() {
        return eventQueue.isEmpty();
    }
}
