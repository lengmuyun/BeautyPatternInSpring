package org.geekbang.time.beautypatterninspring.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmailViewer implements StatViewer {

    private EmailSender emailSender;
    private List<String> toAddress = new ArrayList<>();

    public EmailViewer() {
        this.emailSender = new EmailSender();
    }

    public void addToAddress(String address) {
        toAddress.add(address);
    }

    @Override
    public void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills) {
        // format the requestStats to HTML style.
        // send it to email toAddress
    }

}
