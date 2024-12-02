package com.amalitech.advancedwebsort.Event;

import org.springframework.context.ApplicationEvent;

public class RegistrationCompleteEvent extends ApplicationEvent {
    public RegistrationCompleteEvent(String message) {
        super(message);
    }
}
