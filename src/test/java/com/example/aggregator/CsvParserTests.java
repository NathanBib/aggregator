package com.example.aggregator;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvParserTests {
    @Test
    public void parseLineToMessage() {
        // given
        final var parser = new CsvParser();
        final var line = "1726230942297, DIM_VALUE1, ID1, 42";
        final var header = Map.of(
                "time", 0,
                "dim1", 1,
                "id", 2,
                "cnt_0", 3
        );

        // when
        final var message = parser.parseLine(line, header);

        // then
        final var expected = new Message(
                new MessageKey("202409131230ID1"),
                new Counters(Map.of("cnt_0", 42)),
                new Dimensions(Map.of("dim1", "DIM_VALUE1")),
                new Id("ID1"),
                new Date(1_726_230_942_297L)
        );
        assertEquals(expected, message);
    }
}
