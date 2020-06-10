package org.geekbang.time.beautypatterninspring.ratelimiter.rule.parser;

import org.geekbang.time.beautypatterninspring.ratelimiter.rule.RuleConfig;

import java.io.InputStream;

public interface RuleConfigParser {

    RuleConfig parse(String configText);
    RuleConfig parse(InputStream in);

}
