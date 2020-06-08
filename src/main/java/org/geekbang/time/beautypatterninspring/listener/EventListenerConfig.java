package org.geekbang.time.beautypatterninspring.listener;

import lombok.extern.slf4j.Slf4j;
import org.geekbang.time.beautypatterninspring.event.P2pRegisterEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventListenerConfig {

    @EventListener
    public void postP2pRegisterEvent(P2pRegisterEvent event) {
        log.info("p2puser {} register by annotation @EventListener", event.getUser().getPassword());
    }

    @EventListener
    public void postObjectEvent(Object object) {
        log.info("post Object Event {}", object);
    }

}
