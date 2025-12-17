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

    @GetMapping("/romannumeral") // endpoint
    public ResponseEntity<Map<String, Object>> toRoman(@RequestParam int query) {

        if (!valueCheck(query)) { // checking if query is 1 - 255 before calculating
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Input must be between 1 and 255");
            return ResponseEntity.badRequest().body(error); // 400
        }

        // linked hash map to keep input and output order
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("input", String.valueOf(query));
        response.put("output", integerToRoman(query));
        return ResponseEntity.ok(response); // 200
    }

    // method to check num is not negative, 0 or greater than 255
    public Boolean valueCheck(int num) {

        if (num <= 0 || num > 255) {
            return false;
        }

        return true;

    }

    // adding functionality to calculate roman conversion up to 3999, would need
    // more logic for 4k
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
