package org.geekbang.time.beautypatterninspring.statistics;

public class StatisticsApplication {

    public static void main(String[] args) {
        RedisMetricsStorage storage = new RedisMetricsStorage();
        final ConsoleReporter consoleReporter = new ConsoleReporter(storage, new Aggregator(), new ConsoleViewer());
        consoleReporter.startRepeatedReport(60, 60);

        final EmailViewer statViewer = new EmailViewer();
        EmailReporter emailReporter = new EmailReporter(storage, new Aggregator(), statViewer);
        statViewer.addToAddress("wangzheng@xzg.com");
        emailReporter.startDailyReport();

        MetricsCollector collector = new MetricsCollector(storage);
        collector.recordRequest(new RequestInfo("register", 123, 10234));
        collector.recordRequest(new RequestInfo("register", 223, 11234));
        collector.recordRequest(new RequestInfo("register", 323, 12334));
        collector.recordRequest(new RequestInfo("login", 23, 12434));
        collector.recordRequest(new RequestInfo("login", 1223, 14234));

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
