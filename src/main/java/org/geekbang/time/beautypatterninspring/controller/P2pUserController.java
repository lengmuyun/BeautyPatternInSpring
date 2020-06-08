package org.geekbang.time.beautypatterninspring.controller;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.geekbang.time.beautypatterninspring.entity.P2pUserVo;
import org.geekbang.time.beautypatterninspring.event.P2pRegisterEvent;
import org.geekbang.time.beautypatterninspring.observer.Observer;
import org.geekbang.time.beautypatterninspring.service.P2pUserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/p2p")
@Slf4j
public class P2pUserController implements ApplicationContextAware, ApplicationEventPublisherAware {

    @Autowired
    private P2pUserService p2pUserService;

    private EventBus eventBus;

    private static final int DEFAULT_EVENTBUS_THREAD_POOL_SIZE = 20;
    private ApplicationContext applicationContext;
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
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
        log.info("uri /p2p/register requested.");
        applicationEventPublisher.publishEvent(new P2pRegisterEvent(this, new P2pUserVo(telephone, password)));
        applicationEventPublisher.publishEvent(new Object());
        return userId;
    }

}
