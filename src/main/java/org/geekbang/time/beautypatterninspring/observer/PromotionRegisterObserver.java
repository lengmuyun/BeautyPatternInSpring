package org.geekbang.time.beautypatterninspring.observer;

import com.google.common.eventbus.Subscribe;
import org.geekbang.time.beautypatterninspring.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PromotionRegisterObserver implements Observer {

    @Autowired
    private PromotionService promotionService;

    @Subscribe
    public void handleRegisterSuccess(Long userId) {
        promotionService.issueNewUserExperienceCash(userId);
    }

}
