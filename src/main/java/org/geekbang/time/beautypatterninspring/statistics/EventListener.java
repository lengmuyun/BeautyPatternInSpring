package org.geekbang.time.beautypatterninspring.statistics;

import com.google.common.eventbus.Subscribe;

public class EventListener {

    private MetricsStorage metricsStorage;

    public EventListener(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
    }

    @Subscribe
    public void saveRequestInfo(RequestInfo requestInfo) {
        metricsStorage.saveRequestInfo(requestInfo);
    }

}
