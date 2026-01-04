package com.example.integer_to_roman_api;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

public class IntegerToRomanControllerUnitTests {
    /////////////////////////////////////////////////////////////////////////////////////////
    // Unit Tests Below
    /////////////////////////////////////////////////////////////////////////////////////////

    @Test // tests query as 1 outputs the correct roman symbol
    void testIntegerOne() {
        IntegerToRomanController controller = new IntegerToRomanController();
        ResponseEntity<Map<String, Object>> response = controller.toRoman(1, null, null);

        Map<String, Object> result = response.getBody();

        assertNotNull(result);
        assertEquals("1", result.get("input"));
        assertEquals("I", result.get("output"));

        System.out.println("======================================");
        System.out.println("TEST 1 PASSED: Integer 1 → Roman I");
        System.out.println("======================================");
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    @Test // tests integer above 3999 throws an error
    void testIntegerOutOfUpperBounds() {
        IntegerToRomanController controller = new IntegerToRomanController();
        ResponseEntity<Map<String, Object>> response = controller.toRoman(4000, null, null);

        Map<String, Object> result = response.getBody();

        assertNotNull(result);
        assertEquals("Input must be between 1 and 3999", result.get("error"));

        System.out.println("======================================");
        System.out.println("TEST 2 PASSED: 4000 out of range");
        System.out.println("======================================");
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    @Test // tests integer 0 and below throws an error
    void testIntegerOutOfLowerBounds() {
        IntegerToRomanController controller = new IntegerToRomanController();
        ResponseEntity<Map<String, Object>> response = controller.toRoman(0, null, null);

        Map<String, Object> result = response.getBody();

        assertNotNull(result);
        assertEquals("Input must be between 1 and 3999", result.get("error"));

        System.out.println("======================================");
        System.out.println("TEST 3 PASSED: 0 out of range");
        System.out.println("======================================");
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    @Test // tests only 'min' provided should throw an error
    void testOnlyMinProvided() {
        IntegerToRomanController controller = new IntegerToRomanController();
        ResponseEntity<Map<String, Object>> response = controller.toRoman(null, 5, null);

        Map<String, Object> result = response.getBody();

        assertNotNull(result);
        assertEquals("Provide either 'query' OR both 'min' and 'max'", result.get("error"));

        System.out.println("======================================");
        System.out.println("TEST 4 PASSED: only min provided");
        System.out.println("======================================");
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    @Test // tests only 'max' provided should throw an error
    void testOnlyMaxProvided() {
        IntegerToRomanController controller = new IntegerToRomanController();
        ResponseEntity<Map<String, Object>> response = controller.toRoman(null, null, 10);

        Map<String, Object> result = response.getBody();

        assertNotNull(result);
        assertEquals("Provide either 'query' OR both 'min' and 'max'", result.get("error"));

        System.out.println("======================================");
        System.out.println("TEST 5 PASSED: only max provided");
        System.out.println("======================================");
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    @Test // tests valid range from min=1 to max=10
    void testValidRangeConversion() {
        IntegerToRomanController controller = new IntegerToRomanController();
        ResponseEntity<Map<String, Object>> response = controller.toRoman(null, 1, 10);

        Map<String, Object> result = response.getBody();

        assertNotNull(result);

        // Verify 'conversions' exists
        assertTrue(result.containsKey("conversions"));

        Object conversionsObj = result.get("conversions");
        assertTrue(conversionsObj instanceof List);

        @SuppressWarnings("unchecked")
        List<Map<String, String>> conversions = (List<Map<String, String>>) conversionsObj;

        // There should be exactly 10 conversions
        assertEquals(10, conversions.size());

        // Check first and last conversions
        assertEquals("1", conversions.get(0).get("input"));
        assertEquals("I", conversions.get(0).get("output"));

        assertEquals("10", conversions.get(9).get("input"));
        assertEquals("X", conversions.get(9).get("output"));

        // Check middle value
        assertEquals("5", conversions.get(4).get("input"));
        assertEquals("V", conversions.get(4).get("output"));

        System.out.println("======================================");
        System.out.println("TEST 6 PASSED: valid range 1-10 conversion");
        System.out.println("======================================");
    }
    /////////////////////////////////////////////////////////////////////////////////////////

}
