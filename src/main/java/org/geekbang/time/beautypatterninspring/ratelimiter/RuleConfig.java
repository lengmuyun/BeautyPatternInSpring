package org.geekbang.time.beautypatterninspring.ratelimiter;

import java.util.List;

public class RuleConfig {

    private List<UniformRuleConfig> configs;

    public List<UniformRuleConfig> getConfigs() {
        return configs;
    }

    public void setConfigs(List<UniformRuleConfig> configs) {
        this.configs = configs;
    }

    public static class AppRuleConfig {

        private String appId;
        private List<AppLimit> limits;

        public AppRuleConfig() {
        }

        public AppRuleConfig(String appId, List<AppLimit> limits) {
            this.appId = appId;
            this.limits = limits;
        }

        public String getAppId() {
            return appId;
        }

        public List<AppLimit> getLimits() {
            return limits;
        }
    }

}
