package org.geekbang.time.beautypatterninspring.service;

public interface NotificationService {

    void sendInboxMessage(long userId, String message);

}
