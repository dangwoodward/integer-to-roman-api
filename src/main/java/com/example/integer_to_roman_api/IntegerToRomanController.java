package com.example.integer_to_roman_api;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntegerToRomanController {

    @GetMapping("/romannumeral")
    public ResponseEntity<Map<String, Object>> toRoman(@RequestParam int query) {

        if (!valueCheck(query)) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Input must be between 1 and 255");
            return ResponseEntity.badRequest().body(error); // 400
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("input", String.valueOf(query));
        response.put("output", integerToRoman(query));
        return ResponseEntity.ok(response); // 200
    }

    public Boolean valueCheck(int num) {

        if (num < 0 || num > 255) {
            return false;
        }

        return true;

    }

    private String integerToRoman(int num) {
        int[] integers = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] romans = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

        String result = "";

        for (int i = 0; i < integers.length; i++) {
            while (num >= integers[i]) {
                result += romans[i];
                num -= integers[i];
            }

        }

        return result;
    }
}
