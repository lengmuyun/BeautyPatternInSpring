package org.geekbang.time.beautypatterninspring.service;

import org.geekbang.time.beautypatterninspring.auth.ApiRequest;
import org.geekbang.time.beautypatterninspring.auth.AuthResult;

public interface ApiAuthenticatorService {

    AuthResult<Boolean> auth(String url);

    AuthResult<Boolean> auth(ApiRequest apiRequest);

}
