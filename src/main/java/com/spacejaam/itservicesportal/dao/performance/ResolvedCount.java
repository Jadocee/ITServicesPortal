package com.spacejaam.itservicesportal.dao.performance;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 *
 */
public class ResolvedCount {
    private final LocalDateTime dateTime;
    private final Integer count;

    public ResolvedCount(LocalDateTime dateTime, Integer count) {
        this.dateTime = dateTime;
        this.count = count;
    }

    public String getDateTime() {
        return dateTime.atOffset(ZoneOffset.of(ZoneId.of("Australia/NSW").getId())).format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
    }

    public String getCount() {
        return count.toString();
    }
}
