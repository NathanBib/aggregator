package com.example.aggregator;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AggregatorTests {
    @Test
    public void whenHitLimitReachedComputeAverage() {
        // given
        final var lines = Arrays.asList(
                "1726230942297, DIM_VALUE1, ID1, 42",
                "1726230948297, DIM_VALUE1, ID1, 84"
        );
        final var header = Map.of(
                "time", 0,
                "dim1", 1,
                "id", 2,
                "cnt_0", 3
        );
        final Aggregator aggregator = Aggregator.implementation(header);

        // when
        final var result = aggregator.Aggregate(lines);

        // then
        final var expected = new Message(
                new MessageKey("202409131230ID1"),
                new Counters(Map.of("cnt_0", 63)),
                new Dimensions(Map.of("dim1", "DIM_VALUE1")),
                new Id("ID1"),
                new Date(1_726_230_600_000L)
        );
        assertEquals(expected, result);
    }
}
