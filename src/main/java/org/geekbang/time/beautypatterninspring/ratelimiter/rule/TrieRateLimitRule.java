package org.geekbang.time.beautypatterninspring.ratelimiter.rule;

public class TrieRateLimitRule implements RateLimitRule {

    public TrieRateLimitRule(RuleConfig ruleConfig) {

    }

    @Override
    public ApiLimit getLimit(String appId, String url) {
        return null;
    }

}
