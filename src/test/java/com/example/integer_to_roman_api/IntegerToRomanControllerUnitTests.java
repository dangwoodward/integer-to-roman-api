package com.example.integer_to_roman_api;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

public class IntegerToRomanControllerUnitTests {
    /////////////////////////////////////////////////////////////////////////////////////////
    // Unit Tests Below
    /////////////////////////////////////////////////////////////////////////////////////////

    @Test // tests query as 1 outputs the correct roman symbol
    void testIntegerOne() {
        IntegerToRomanController controller = new IntegerToRomanController();
        ResponseEntity<Map<String, Object>> response = controller.toRoman(1);

        Map<String, Object> result = response.getBody();

        assertNotNull(result);
        assertEquals("1", result.get("input"));
        assertEquals("I", result.get("output"));

        System.out.println("======================================");
        System.out.println("TEST 1 PASSED: Integer 1 → Roman I");
        System.out.println("======================================");
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    @Test // tests integer above 255 throws an error
    void testIntegerOutOfUpperBounds() {
        IntegerToRomanController controller = new IntegerToRomanController();
        ResponseEntity<Map<String, Object>> response = controller.toRoman(300);

        Map<String, Object> result = response.getBody();

        assertNotNull(result);
        assertEquals("Input must be between 1 and 255", result.get("error"));

        System.out.println("======================================");
        System.out.println("TEST 2 PASSED: 300 out of range");
        System.out.println("======================================");
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    @Test // tests integer 0 and below throws an error
    void testIntegerOutOfLowerBounds() {
        IntegerToRomanController controller = new IntegerToRomanController();
        ResponseEntity<Map<String, Object>> response = controller.toRoman(0);

        Map<String, Object> result = response.getBody();

        assertNotNull(result);
        assertEquals("Input must be between 1 and 255", result.get("error"));

        System.out.println("======================================");
        System.out.println("TEST 3 PASSED: 0 out of range");
        System.out.println("======================================");
    }
    /////////////////////////////////////////////////////////////////////////////////////////
}
