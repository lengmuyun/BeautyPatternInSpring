package org.geekbang.time.beautypatterninspring.service.impl;

import org.geekbang.time.beautypatterninspring.service.PromotionService;
import org.springframework.stereotype.Service;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Override
    public void issueNewUserExperienceCash(long userId) {
        System.out.println("PromotionServiceImpl method issueNewUserExperienceCash invoked, param");
    }

}
