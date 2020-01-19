package org.geekbang.time.beautypatterninspring.auth;

import com.google.gson.Gson;
import org.geekbang.time.beautypatterninspring.service.ApiAuthenticatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private ApiAuthenticatorService apiAuthenticatorService;

    private Gson gson = new Gson();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String appId = request.getParameter("appId");
        long createTime = request.getParameter("createTime") == null ? 0L : Long.parseLong(request.getParameter("createTime"));
        String token = request.getParameter("token");

        ApiRequest apiRequest = new ApiRequest(uri, token, appId, createTime);
        AuthResult auth = apiAuthenticatorService.auth(apiRequest);
        if (!auth.isSuccess()) {
            writeResponse(response, auth);
            return false;
        }
        return true;
    }

    private void writeResponse(HttpServletResponse response, AuthResult result) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.getWriter().print(gson.toJson(result));
    }

}
