package org.geekbang.time.beautypatterninspring.ratelimiter;

public class AppLimit {

    private static final int DEFAULT_TIME_UNIT = 1;
    private String api;
    private int limit;
    private int unit = DEFAULT_TIME_UNIT;

    public AppLimit() {
    }

    public AppLimit(String api, int limit) {
        this(api, limit, DEFAULT_TIME_UNIT);
    }

    public AppLimit(String api, int limit, int unit) {
        this.api = api;
        this.limit = limit;
        this.unit = unit;
    }

    public String getApi() {
        return api;
    }

    public int getLimit() {
        return limit;
    }

    public int getUnit() {
        return unit;
    }

}
