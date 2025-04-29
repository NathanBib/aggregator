package com.example.aggregator;

import java.util.Date;

public record Message(
        MessageKey messageKey,
        Counters counters,
        Dimensions dimensions,
        Id id,
        Date date
) {
}
