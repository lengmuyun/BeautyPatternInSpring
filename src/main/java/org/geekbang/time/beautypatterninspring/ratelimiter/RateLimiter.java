package org.geekbang.time.beautypatterninspring.ratelimiter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

public class RateLimiter {

    private static final Logger log = LoggerFactory.getLogger(RateLimiter.class);

    // 为每个api在内存中存储限流计数器
    private ConcurrentHashMap<String, FixedTimeWinRateLimitAlg> counters = new ConcurrentHashMap<>();
    private RateLimitRule rule;

    public RateLimiter() {
        //改动主要在这里：调用RuleConfigSource类来实现配置加载
        FileRuleConfigSource fileRuleConfigSource = new FileRuleConfigSource();
        RuleConfig ruleConfig = fileRuleConfigSource.load();
        // 将限流规则构建成支持快速查找的数据结构RateLimitRule
        this.rule = new TrieRateLimitRule(ruleConfig);
    }

    public boolean limit(String appId, String url) {
        ApiLimit apiLimit = rule.getLimit(appId, url);
        if (apiLimit == null) return true;

        // 获取api对应在内存中的限流计数器
        String counterKey = appId + ":" + apiLimit.getApi();
        FixedTimeWinRateLimitAlg rateLimitCounter = counters.get(counterKey);
        if (rateLimitCounter == null) {
            FixedTimeWinRateLimitAlg newRateLimitCounter = new FixedTimeWinRateLimitAlg(apiLimit.getLimit());
            rateLimitCounter = counters.putIfAbsent(counterKey, newRateLimitCounter);
            if (rateLimitCounter == null) {
                rateLimitCounter = newRateLimitCounter;
            }
        }

        // 判断是否限流
        return rateLimitCounter.tryAcquire();
    }

}
