package org.geekbang.time.beautypatterninspring.statistics;

import java.util.Map;

public interface StatViewer {

    void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills);

}
