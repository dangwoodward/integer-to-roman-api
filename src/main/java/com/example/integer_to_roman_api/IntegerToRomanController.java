package com.example.integer_to_roman_api;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntegerToRomanController {

    // Global logger for this class
    private static final Logger logger = LoggerFactory.getLogger(IntegerToRomanController.class);

    @GetMapping("/romannumeral")
    public ResponseEntity<Map<String, Object>> toRoman(
            @RequestParam(required = false) Integer query,
            @RequestParam(required = false) Integer min,
            @RequestParam(required = false) Integer max) {

        logger.info("Received request: query={}, min={}, max={}", query, min, max);

        // Single query
        if (query != null) {
            if (!valueCheck(query)) {
                logger.warn("Query {} out of bounds", query);
                return badRequest("Input must be between 1 and 3999");
            }

            String roman = integerToRoman(query);
            logger.debug("Converted {} to {}", query, roman);

            return ResponseEntity.ok(Map.of(
                    "input", String.valueOf(query),
                    "output", roman));
        }

        // Range query
        if (min != null && max != null) {
            if (!rangeCheck(min, max)) {
                logger.warn("Invalid range: min={}, max={}", min, max);
                return badRequest("min and max must be between 1 and 3999, and min <= max");
            }

            ExecutorService executor = Executors.newFixedThreadPool(
                    Runtime.getRuntime().availableProcessors());

            List<Future<Map<String, String>>> futures = new ArrayList<>();

            for (int i = min; i <= max; i++) {
                final int value = i;
                futures.add(executor.submit(() -> {
                    Map<String, String> conversion = new LinkedHashMap<>();
                    conversion.put("input", String.valueOf(value));
                    conversion.put("output", integerToRoman(value));
                    return conversion;
                }));
            }

            List<Map<String, String>> conversions = new ArrayList<>();

            try {
                for (Future<Map<String, String>> future : futures) {
                    conversions.add(future.get());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Request interrupted", e);
                return ResponseEntity.internalServerError()
                        .body(Map.of("error", "Request interrupted"));
            } catch (ExecutionException e) {
                logger.error("Conversion failed", e);
                return ResponseEntity.internalServerError()
                        .body(Map.of("error", "Conversion failed"));
            } finally {
                executor.shutdown();
            }

            logger.info("Completed range conversion: min={}, max={}", min, max);
            return ResponseEntity.ok(Map.of(
                    "conversions", conversions));
        }

        logger.warn("Invalid request parameters: query={}, min={}, max={}", query, min, max);
        return badRequest("Provide either 'query' OR both 'min' and 'max'");
    }

    private ResponseEntity<Map<String, Object>> badRequest(String message) {
        logger.warn("Bad request: {}", message);
        return ResponseEntity.badRequest()
                .body(Map.of("error", message));
    }

    private boolean valueCheck(int num) {
        return num >= 1 && num <= 3999;
    }

    private boolean rangeCheck(int min, int max) {
        return min >= 1 && max <= 3999 && min <= max;
    }

    private String integerToRoman(int num) {
        int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] numerals = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                result.append(numerals[i]);
                num -= values[i];
            }
        }

        return result.toString();
    }
}
