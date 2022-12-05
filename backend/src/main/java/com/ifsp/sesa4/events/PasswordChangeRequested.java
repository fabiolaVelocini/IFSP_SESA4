package com.ifsp.sesa4.events;

import com.ifsp.sesa4.entities.User;
import org.springframework.context.ApplicationEvent;

public class PasswordChangeRequested extends ApplicationEvent {

    public final User user;

    public PasswordChangeRequested(Object source, User savedUser) {
        super(source);
        this.user = savedUser;
    }
}
