package org.geekbang.time.beautypatterninspring.controller;

import org.geekbang.time.beautypatterninspring.observer.RegisterObserver;
import org.geekbang.time.beautypatterninspring.service.P2pUserService;
import org.geekbang.time.beautypatterninspring.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/p2p")
public class P2pUserController {

    @Autowired
    private P2pUserService p2pUserService;

    @Autowired
    private PromotionService promotionService;

    private List<RegisterObserver> observers = new ArrayList<>();

    public void registerObserver(RegisterObserver observer) {
        observers.add(observer);
    }

    @PostMapping("/register")
    public Long register(String telephone, String password) {
        long userId = p2pUserService.register(telephone, password);
        observers.forEach(o -> o.handleRegisterSuccess(userId));
        return userId;
    }

}
