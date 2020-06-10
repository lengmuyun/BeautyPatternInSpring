package org.geekbang.time.beautypatterninspring.ratelimiter.rule.datasource;

import org.geekbang.time.beautypatterninspring.ratelimiter.rule.RuleConfig;

public interface RuleConfigSource {

    RuleConfig load();

}
