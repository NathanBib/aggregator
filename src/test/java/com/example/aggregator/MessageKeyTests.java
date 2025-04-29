package com.example.aggregator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageKeyTests {
    @Test
    public void givenIdAndDateGetMessageKey() {
        // given
        final var id = "ID1";
        final var tstamp = 1726230948297L;

        // when
        final var messageKey = MessageKey.from(id, tstamp);

        // then
        final var expected = new MessageKey("202409131230ID1");
        assertEquals(expected, messageKey);
    }


}
