package org.geekbang.time.beautypatterninspring.observer;

import org.geekbang.time.beautypatterninspring.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PromotionRegisterObserver implements RegisterObserver {

    @Autowired
    private PromotionService promotionService;

    @Override
    public void handleRegisterSuccess(long userId) {
        promotionService.issueNewUserExperienceCash(userId);
    }

}
