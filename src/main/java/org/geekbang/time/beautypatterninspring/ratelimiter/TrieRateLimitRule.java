package org.geekbang.time.beautypatterninspring.ratelimiter;

public class TrieRateLimitRule implements RateLimitRule {

    public TrieRateLimitRule(RuleConfig ruleConfig) {

    }

    @Override
    public ApiLimit getLimit(String appId, String url) {
        return null;
    }
}
