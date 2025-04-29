package com.example.aggregator;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CsvParser {
    public Message parseLine(String line, Map<String, Integer> header) {
        final var fields = line.split(", ");
        var tstamp = 0L;
        Map<String, String> dims = new HashMap<>();
        var id = "";
        Map<String, Integer> counters = new HashMap<>();
        for (String fieldName:header.keySet()) {
            if (fieldName.startsWith("time")) {
                tstamp = Long.parseLong(fields[header.get(fieldName)]);
                continue;
            }
            if (fieldName.startsWith("dim")) {
                dims.put(fieldName, fields[header.get(fieldName)]);
                continue;
            }
            if (fieldName.startsWith("id")) {
                id = fields[header.get(fieldName)];
                continue;
            }
            if (fieldName.startsWith("cnt")) {
                counters.put(fieldName, Integer.parseInt(fields[header.get(fieldName)]));
            }
        }
        return new Message(
                MessageKey.from(id, tstamp),
                new Counters(counters),
                new Dimensions(dims),
                new Id(id),
                new Date(tstamp)
        );
    }
}
