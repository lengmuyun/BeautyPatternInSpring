package org.geekbang.time.beautypatterninspring.ratelimiter.rule;

public interface RateLimitRule {

    ApiLimit getLimit(String appId, String url);

}
