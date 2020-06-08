package org.geekbang.time.beautypatterninspring.controller;

import org.geekbang.time.beautypatterninspring.entity.UserVo;
import org.geekbang.time.beautypatterninspring.event.RegisterEvent;
import org.geekbang.time.beautypatterninspring.statistics.Metrics;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController implements ApplicationEventPublisherAware {

    private Metrics metrics = new Metrics();

    private ApplicationEventPublisher applicationEventPublisher;

    public UserController() {
        metrics.startRepeatedReport(60, TimeUnit.SECONDS);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @PostMapping("/register")
    public void register(@RequestBody UserVo user) {
        final long startTimestamp = System.currentTimeMillis();
        metrics.recordTimestamp("register", startTimestamp);

        // ... do register

        final long respTime = System.currentTimeMillis() - startTimestamp;
        metrics.recordResponseTime("register", respTime);

        applicationEventPublisher.publishEvent(new RegisterEvent(this, user));
    }

    @PostMapping("/login")
    public UserVo login(String telephone, String password) {
        final long startTimestamp = System.currentTimeMillis();
        metrics.recordTimestamp("login", startTimestamp);

        // ... do login

        final long respTime = System.currentTimeMillis() - startTimestamp;
        metrics.recordResponseTime("login", respTime);
        return new UserVo();
    }

}
