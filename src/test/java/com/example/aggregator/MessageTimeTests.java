package com.example.aggregator;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageTimeTests {
    @Test
    public void formatUTCDate() {
        // given
        final var messageTime = new MessageTime(1726230948297L);

        // when
        final var formatted = messageTime.format();

        // then
        final var expected = "202409131235";
        assertEquals(expected, formatted);
    }

    @Test
    public void truncateToQuarterOfHour() {
        // given
        final var tstamp = 1_726_230_948_297L;
        final var messageTime = new MessageTime(tstamp);

        // when
        final var truncatedToQuarter = messageTime.truncatedToQuarter();

        // then
        final var expected = new Date(1_726_230_600_000L);
        assertEquals(expected, truncatedToQuarter);
    }
}
