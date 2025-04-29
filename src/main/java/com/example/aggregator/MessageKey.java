package com.example.aggregator;

public record MessageKey(
        String value
) {
    public static MessageKey from(String id, long tstamp) {
        final var messageTime = new MessageTime(tstamp);
        final var truncated = messageTime.truncatedToQuarter();
        final var formatted = new MessageTime(truncated).format();
        return new MessageKey(formatted + id);
    }

}