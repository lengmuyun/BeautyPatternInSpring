package org.geekbang.time.beautypatterninspring.darklaunch;

import java.util.Map;
import java.util.stream.Collectors;

public class DarkRule {

    private Map<String, DarkFeature> darkFeatures;

    public DarkRule(DarkRuleConfig ruleConfig) {
        darkFeatures = ruleConfig.getFeatures()
                .stream()
                .collect(Collectors.toMap(DarkRuleConfig.DarkFeatureConfig::getKey, DarkFeature::new));
    }

    public DarkFeature getDarkFeature(String featureKey) {
        return darkFeatures.get(featureKey);
    }

}
