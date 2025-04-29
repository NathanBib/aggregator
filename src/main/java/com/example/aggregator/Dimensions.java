package com.example.aggregator;

import java.util.Map;

public record Dimensions(
        Map<String, String> dimensions
) {
}
