package org.geekbang.time.beautypatterninspring.observer;

import org.springframework.stereotype.Component;

@Component
public class NotificationRegisterObserver implements RegisterObserver {

    @Override
    public void handleRegisterSuccess(long userId) {

    }

}
