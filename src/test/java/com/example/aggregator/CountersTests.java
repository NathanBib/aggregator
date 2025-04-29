package com.example.aggregator;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountersTests {
    @Test
    public void aggregateCountersTest() {
        // given
        final var summedCounters = new Counters(Map.of("cnt", 126));
        final var countersCount = Map.of("cnt", 2);

        // when
        final var avgCounters = summedCounters.average(countersCount);

        // then
        final var expected = new Counters(Map.of("cnt", 63));
        assertEquals(expected, avgCounters);
    }
}
