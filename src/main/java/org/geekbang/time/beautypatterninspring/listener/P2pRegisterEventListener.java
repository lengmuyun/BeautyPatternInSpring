package org.geekbang.time.beautypatterninspring.listener;

import lombok.extern.slf4j.Slf4j;
import org.geekbang.time.beautypatterninspring.event.P2pRegisterEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class P2pRegisterEventListener implements ApplicationListener<P2pRegisterEvent> {

    @Async
    @Override
    public void onApplicationEvent(P2pRegisterEvent event) {
        log.info("p2puser {} register!!!!", event.getUser().getPassword());
    }

}
