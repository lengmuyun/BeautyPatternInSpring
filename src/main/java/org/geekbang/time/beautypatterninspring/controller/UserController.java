package org.geekbang.time.beautypatterninspring.controller;

import org.geekbang.time.beautypatterninspring.entity.UserVo;
import org.geekbang.time.beautypatterninspring.statistics.Metrics;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {

    private Metrics metrics = new Metrics();

    public UserController() {
        metrics.startRepeatedReport(60, TimeUnit.SECONDS);
    }

    @PostMapping("/register")
    public void register(UserVo user) {
        final long startTimestamp = System.currentTimeMillis();
        metrics.recordTimestamp("register", startTimestamp);

        // ... do register

        final long respTime = System.currentTimeMillis() - startTimestamp;
        metrics.recordResponseTime("register", respTime);
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
