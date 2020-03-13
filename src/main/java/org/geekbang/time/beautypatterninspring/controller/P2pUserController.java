package org.geekbang.time.beautypatterninspring.controller;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.geekbang.time.beautypatterninspring.observer.Observer;
import org.geekbang.time.beautypatterninspring.service.P2pUserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/p2p")
public class P2pUserController implements ApplicationContextAware {

    @Autowired
    private P2pUserService p2pUserService;

    private EventBus eventBus;

    private static final int DEFAULT_EVENTBUS_THREAD_POOL_SIZE = 20;
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void initializeEventBus() {
//        eventBus = new EventBus(); // 同步阻塞模式
        eventBus = new AsyncEventBus(Executors.newFixedThreadPool(DEFAULT_EVENTBUS_THREAD_POOL_SIZE));

        // 注册观察者
        Map<String, Observer> observers = applicationContext.getBeansOfType(Observer.class);
        observers.values().forEach(observer -> eventBus.register(observer));
    }

    @PostMapping("/register")
    public Long register(String telephone, String password) {
        Long userId = p2pUserService.register(telephone, password);
        eventBus.post(userId);
        return userId;
    }

}
