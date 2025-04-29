package com.example.aggregator;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public record MessageTime(
        long tstamp
) {
    public MessageTime(Date date) {
        this(date.getTime());
    }

    public Date truncatedToQuarter() {
        final var interval = 15 * 60 * 1000L;
        return new Date((this.tstamp/interval)*interval);
    }

    public String format() {
        final var instant = new Date(this.tstamp).toInstant();
        final var utcDate = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        final var formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        return utcDate.format(formatter);
    }
}
