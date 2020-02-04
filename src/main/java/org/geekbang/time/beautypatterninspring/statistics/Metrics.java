package org.geekbang.time.beautypatterninspring.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Metrics {

    // Map的key是接口名称，value对应接口请求的响应时间或时间错
    private Map<String, List<Double>> responseTimes = new HashMap<>();
    private Map<String, List<Double>> timestamps = new HashMap<>();
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public void recordResponseTime(String apiName, double responseTime) {
        responseTimes.putIfAbsent(apiName, new ArrayList<>());
        responseTimes.get(apiName).add(responseTime);
    }

    public void recordTimestamp(String apiName, double timestamp) {
        timestamps.putIfAbsent(apiName, new ArrayList<>());
        timestamps.get(apiName).add(timestamp);
    }

    public void startRepeatedReport(long period, TimeUnit unit) {
        executor.scheduleAtFixedRate(() -> {
            Map<String, Map<String, Double>> stats = new HashMap<>();
            for (Map.Entry<String, List<Double>> entry : responseTimes.entrySet()) {
                final String apiName = entry.getKey();
                final List<Double> apiRespTimes = entry.getValue();
                stats.putIfAbsent(apiName, new HashMap<>());
                stats.get(apiName).put("max", max(apiRespTimes));
                stats.get(apiName).put("avg", avg(apiRespTimes));
            }
            for (Map.Entry<String, List<Double>> entry : timestamps.entrySet()) {
                final String apiName = entry.getKey();
                final List<Double> apiTimestamps = entry.getValue();
                stats.putIfAbsent(apiName, new HashMap<>());
                stats.get(apiName).put("count", (double) apiTimestamps.size());
            }
        }, 0, period, unit);
    }

    private Double avg(List<Double> apiRespTimes) {
        return apiRespTimes.stream().collect(Collectors.averagingDouble(d -> d));
    }

    private Double max(List<Double> apiRespTimes) {
        final Optional<Double> max = apiRespTimes.stream().max(Double::compareTo);
        return max.orElse(null);
    }

}
