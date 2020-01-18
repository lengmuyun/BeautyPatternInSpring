package org.geekbang.time.beautypatterninspring.auth;

import org.geekbang.time.beautypatterninspring.service.ApiAuthenticatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private ApiAuthenticatorService apiAuthenticatorService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String appId = request.getParameter("appId");
        long createTime = request.getParameter("createTime") == null ? 0L : Long.parseLong(request.getParameter("createTime"));
        String token = request.getParameter("token");

        ApiRequest apiRequest = new ApiRequest(uri, token, appId, createTime);
        AuthResult<Boolean> auth = apiAuthenticatorService.auth(apiRequest);
        if (!auth.getData()) {
            System.out.println(auth.getMessage());
        }
        return auth.getData();
    }

}
