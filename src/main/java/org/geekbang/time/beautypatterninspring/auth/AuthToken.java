package org.geekbang.time.beautypatterninspring.auth;

import org.geekbang.time.beautypatterninspring.util.MD5Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class AuthToken {

    private static final long DEFAULT_EXPIRED_TIME_INTERVAL = 5 * 60 * 1000;
    private String token;
    private long createTime;
    private long expiredTimeInterval = DEFAULT_EXPIRED_TIME_INTERVAL;

    public AuthToken(String token, long createTime) {
        this.token = token;
        this.createTime = createTime;
    }

    public AuthToken(String token, long createTime, long expiredTimeInterval) {
        this.token = token;
        this.createTime = createTime;
        this.expiredTimeInterval = expiredTimeInterval;
    }

    /**
     * 把 URL、AppID、密码、时间戳拼接为一个字符串；对字符串通过加密算法加密生成 token；
     * @param baseUrl
     * @param createTime
     * @param params
     * @return
     */
    public static AuthToken create(String baseUrl, long createTime, Map<String, String> params) {
        StringBuilder sb = new StringBuilder(baseUrl);
        sb.append('?').append("createTime=" + createTime);
        appendParamsByKeys(params, sb);
        String fullUrl = sb.toString();
        return new AuthToken(MD5Utils.getMD5(fullUrl), createTime);
    }

    private static void appendParamsByKeys(Map<String, String> params, StringBuilder sb) {
        List<String> keys = new ArrayList<>(params.keySet());
        keys.sort(String::compareTo);
        keys.forEach(k -> sb.append("&" + k + "=" + params.get(k)));
    }

    public String getToken() {
        return token;
    }

    /**
     * 根据时间戳判断 token 是否过期失效
     * @return
     */
    public boolean isExpired() {
        long now = System.currentTimeMillis();
        return now - createTime > expiredTimeInterval;
    }

    /**
     * 验证两个 token 是否匹配
     * @param authToken
     * @return
     */
    public boolean match(AuthToken authToken) {
        return this.token.equals(authToken.getToken());
    }

}