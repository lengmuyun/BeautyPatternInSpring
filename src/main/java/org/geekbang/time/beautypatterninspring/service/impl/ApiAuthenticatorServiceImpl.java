package org.geekbang.time.beautypatterninspring.service.impl;

import org.geekbang.time.beautypatterninspring.auth.ApiRequest;
import org.geekbang.time.beautypatterninspring.auth.AuthResult;
import org.geekbang.time.beautypatterninspring.auth.AuthToken;
import org.geekbang.time.beautypatterninspring.service.ApiAuthenticatorService;
import org.geekbang.time.beautypatterninspring.service.CredentialStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ApiAuthenticatorServiceImpl implements ApiAuthenticatorService {

    @Autowired
    private CredentialStorageService credentialStorageService;

    @Override
    public AuthResult<Boolean> auth(String url) {
        ApiRequest apiRequest = ApiRequest.createFromFullUrl(url);
        return auth(apiRequest);
    }

    @Override
    public AuthResult<Boolean> auth(ApiRequest apiRequest) {
        String appId = apiRequest.getAppId();
        long timestamp = apiRequest.getTimestamp();

        AuthToken clientAuthToken = new AuthToken(apiRequest.getToken(), timestamp);
        if (clientAuthToken.isExpired()) {
            return new AuthResult<>(Boolean.FALSE, AuthResult.AuthMessage.TOKEN_IS_EXPIRED);
        }

        String password = credentialStorageService.getPasswordByAppId(appId);
        HashMap<String, String> params = new HashMap<>();
        params.put("appId", appId);
        params.put("password", password);
        AuthToken serverAutoToken = AuthToken.create(apiRequest.getBaseUrl(), timestamp, params);
        if (!serverAutoToken.match(clientAuthToken)) {
            return new AuthResult<>(Boolean.FALSE, AuthResult.AuthMessage.TOKEN_VERFICATION_FAILED);
        }
        return new AuthResult<>(Boolean.TRUE, AuthResult.AuthMessage.SUCCESS);
    }

}
