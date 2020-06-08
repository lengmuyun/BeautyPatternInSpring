package org.geekbang.time.beautypatterninspring.listener;

import lombok.extern.slf4j.Slf4j;
import org.geekbang.time.beautypatterninspring.event.RegisterEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RegisterEventListener implements ApplicationListener<RegisterEvent> {

    @Override
    public void onApplicationEvent(RegisterEvent registerEvent) {
        log.info("user {} register!!!!", registerEvent.getUser().getPassword());
    }

}
