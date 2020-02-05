package org.geekbang.time.beautypatterninspring.statistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Aggregator {

    public Map<String, RequestStat> aggregate(Map<String, List<RequestInfo>> requestInfos, long durationInMillis) {
        Map<String, RequestStat> stats = new HashMap<>();
        for (Map.Entry<String, List<RequestInfo>> entry : requestInfos.entrySet()) {
            String apiName = entry.getKey();
            List<RequestInfo> requestInfosPerApi = entry.getValue();
            // 第2个代码逻辑：根据原始数据，计算得到统计数据；
            RequestStat requestStat = doAggregate(requestInfosPerApi, durationInMillis);
            stats.put(apiName, requestStat);
        }
        return stats;
    }

    public RequestStat doAggregate(List<RequestInfo> requestInfos, long durationInMillis) {
        List<Double> responseTimeList = requestInfos.stream()
                .map(RequestInfo::getResponseTime)
                .sorted()
                .collect(Collectors.toList());

        RequestStat requestStat = new RequestStat();
        requestStat.setMaxResponseTime(responseTimeList.get(responseTimeList.size() - 1));
        requestStat.setMinResponseTime(responseTimeList.get(0));
        requestStat.setAvgResponseTime(avg(responseTimeList));
        requestStat.setP999ResponseTime(percentile999(responseTimeList));
        requestStat.setP99ResponseTime(percentile99(responseTimeList));
        requestStat.setCount(responseTimeList.size());
        requestStat.setTps(tps(responseTimeList.size(), durationInMillis / 1000));
        return requestStat;
    }

    private long tps(int size, long l) {
        return 0;
    }

    private double percentile99(List<Double> responseTimeList) {
        return 0;
    }

    private double percentile999(List<Double> responseTimeList) {
        return 0;
    }

    private double avg(List<Double> responseTimeList) {
        return 0;
    }

}
