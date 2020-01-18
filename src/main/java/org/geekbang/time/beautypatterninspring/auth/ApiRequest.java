package org.geekbang.time.beautypatterninspring.auth;

import java.util.stream.Stream;

public class ApiRequest {

    private String baseUrl;
    private String token;
    private String appId;
    private long timestamp;

    public ApiRequest(String baseUrl, String token, String appId, long timestamp) {
        this.baseUrl = baseUrl;
        this.token = token;
        this.appId = appId;
        this.timestamp = timestamp;
    }

    private ApiRequest() {
    }

    /**
     * 解析 URL，得到 token、AppID、时间戳等信息，并返回ApiRequest
     * @param url
     * @return
     */
    public static ApiRequest createFromFullUrl(String url) {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.baseUrl = url.substring(0, url.indexOf('?'));
        String params = url.substring(url.indexOf('?') + 1);
        Stream.of(params.split("&")).forEach(p -> {
            String[] kv = p.split("=");
            if ("token".equals(kv[0])) {
                apiRequest.token = kv[1];
            } else if ("appId".equals(kv[0])) {
                apiRequest.appId = kv[1];
            } else if ("timestamp".equals(kv[0])) {
                apiRequest.timestamp = Long.parseLong(kv[1]);
            }
        });
        return apiRequest;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getToken() {
        return token;
    }

    public String getAppId() {
        return appId;
    }

    public long getTimestamp() {
        return timestamp;
    }

}
