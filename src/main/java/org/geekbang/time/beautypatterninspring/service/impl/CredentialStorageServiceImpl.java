package org.geekbang.time.beautypatterninspring.service.impl;

import org.geekbang.time.beautypatterninspring.service.CredentialStorageService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CredentialStorageServiceImpl implements CredentialStorageService {

    private static final Map<String, String> memoryStorage = new HashMap<>();

    static {
        memoryStorage.put("fkz", "123456");
    }

    @Override
    public String getPasswordByAppId(String appId) {
        return memoryStorage.get(appId);
    }

}
