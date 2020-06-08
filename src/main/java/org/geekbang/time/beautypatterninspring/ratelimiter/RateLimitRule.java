package org.geekbang.time.beautypatterninspring.ratelimiter;

public interface RateLimitRule {

    ApiLimit getLimit(String appId, String url);

}
