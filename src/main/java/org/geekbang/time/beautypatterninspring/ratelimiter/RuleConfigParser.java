package org.geekbang.time.beautypatterninspring.ratelimiter;

import java.io.InputStream;

public interface RuleConfigParser {

    RuleConfig parse(String configText);
    RuleConfig parse(InputStream in);

}
