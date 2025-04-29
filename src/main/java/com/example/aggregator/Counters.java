package com.example.aggregator;

import java.util.HashMap;
import java.util.Map;

public record Counters(
        Map<String, Integer> counters
) {
    public Counters average(Map<String, Integer> countersCount) {
        Map<String, Integer> result = new HashMap<>();
        for (final var counterName : countersCount.keySet()) {
            result.put(counterName, this.counters.get(counterName)/countersCount.get(counterName));
        }
        return new Counters(result);
    }
}
