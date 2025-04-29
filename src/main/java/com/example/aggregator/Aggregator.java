package com.example.aggregator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aggregator {
    private final Map<String, Integer> header;

    public Aggregator(Map<String, Integer> header) {
        this.header = header;
    }

    public Message Aggregate(List<String> lines) {
        final var parser = new CsvParser();
        Message result = null;
        Map<String, Integer> countersCount = new HashMap<>();
        Map<String, Integer> counters = new HashMap<>();
        for (final var line : lines) {
            final var message = parser.parseLine(line, header);
            for (final var counterName : message.counters().counters().keySet()) {
                int value = message.counters().counters().get(counterName);
                if (countersCount.containsKey(counterName)) {
                    countersCount.put(counterName, countersCount.get(counterName) + 1);
                    final var previousValue = counters.get(counterName);
                    counters.put(counterName, previousValue + value);
                } else {
                    countersCount.put(counterName, 1);
                    counters.put(counterName, value);
                }
            }
            final var date = new MessageTime(message.date()).truncatedToQuarter();
            result = new Message(
                    message.messageKey(),
                    new Counters(counters).average(countersCount),
                    message.dimensions(),
                    message.id(),
                    date
            );
        }
        return result;
    }
}
