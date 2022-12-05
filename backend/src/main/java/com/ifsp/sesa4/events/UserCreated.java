package com.ifsp.sesa4.events;

import com.ifsp.sesa4.entities.User;
import org.springframework.context.ApplicationEvent;

public class UserCreated extends ApplicationEvent {

    public final User user;

    public  UserCreated(Object source, User savedUser) {
        super(source);
        this.user = savedUser;
    }

}
