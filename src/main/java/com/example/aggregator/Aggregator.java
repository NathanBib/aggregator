package com.example.aggregator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
en entrée :
time,dim_1,dim_2,id,cnt_1,cnt_2
1234,titi1,toto2,12,    1,    2
1234,titi1,toto2,23,    3,    4

time,dim_1,dim_2,id,cnt_1,cnt_2
2345,titi1,toto2,12,    5,    6
2345,titi1,toto2,23,    7,    8

en sortie :
1230,titi1,toto2,12,    3,    4
1230,titi1,toto2,23,    5,    6
 */

@FunctionalInterface
public interface Aggregator {
    Message Aggregate(List<String> lines);
    static Aggregator implementation(Map<String, Integer> header) {
        return lines -> {
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
        };
    }
}
