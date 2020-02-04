package org.geekbang.time.beautypatterninspring.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestInfo {

    private String apiName;
    private double responseTime;
    private long timestamp;

}
