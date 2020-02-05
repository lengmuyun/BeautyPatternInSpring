package org.geekbang.time.beautypatterninspring.statistics;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailReporterTest {

    @Test
    void trimTimeFieldsToZeroOfNextDay() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        Date date = sdf.parse("2020-01-04 00:00:00");
        EmailReporter emailReporter = new EmailReporter(null, null, null);
        Date nextDay = emailReporter.trimTimeFieldsToZeroOfNextDay(date);
        long oneDay = 24 * 60 * 60 * 1000;
        assertEquals(oneDay, nextDay.getTime() - date.getTime());
    }

}