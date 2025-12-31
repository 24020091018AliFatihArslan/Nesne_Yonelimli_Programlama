package com.ali.hospital.event;

import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {
    private final CopyOnWriteArrayList<EventListener> listeners = new CopyOnWriteArrayList<>();
    public void subscribe(EventListener l){ listeners.add(l); }
    public void publish(AppEvent e){ for(EventListener l: listeners){ try{ l.onEvent(e);}catch(Exception ignored){} } }
}
