package org.geekbang.time.beautypatterninspring.observer;

import com.google.common.eventbus.Subscribe;
import org.springframework.stereotype.Component;

@Component
public class NotificationRegisterObserver implements Observer {

    @Subscribe
    public void handleRegisterSuccess(Long userId) {
        System.out.println("NotificationRegisterObserver method handleRegisterSuccess invoked, param " + userId);
    }

}
