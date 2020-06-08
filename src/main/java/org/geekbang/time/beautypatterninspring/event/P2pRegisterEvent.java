package org.geekbang.time.beautypatterninspring.event;

import org.geekbang.time.beautypatterninspring.entity.P2pUserVo;
import org.springframework.context.ApplicationEvent;

public class P2pRegisterEvent extends ApplicationEvent {

    private final P2pUserVo user;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public P2pRegisterEvent(Object source, P2pUserVo user) {
        super(source);
        this.user = user;
    }

    public P2pUserVo getUser() {
        return user;
    }

}
