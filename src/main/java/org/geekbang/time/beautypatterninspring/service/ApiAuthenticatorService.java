package org.geekbang.time.beautypatterninspring.service;

import org.geekbang.time.beautypatterninspring.auth.ApiRequest;
import org.geekbang.time.beautypatterninspring.auth.AuthResult;

public interface ApiAuthenticatorService {

    AuthResult auth(String url);

    AuthResult auth(ApiRequest apiRequest);

}
