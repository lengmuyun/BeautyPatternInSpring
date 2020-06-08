package org.geekbang.time.beautypatterninspring.event;

import org.geekbang.time.beautypatterninspring.entity.UserVo;
import org.springframework.context.ApplicationEvent;

public class RegisterEvent extends ApplicationEvent {

    private final UserVo user;

    public RegisterEvent(Object source, UserVo user) {
        super(source);
        this.user = user;
    }

    public UserVo getUser() {
        return user;
    }

}
