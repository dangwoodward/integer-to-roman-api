package com.example.integer_to_roman_api;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

public class IntegerToRomanControllerUnitTests {

    @Test
    void testIntegerOne() {
        IntegerToRomanController controller = new IntegerToRomanController();
        ResponseEntity<Map<String, Object>> response = controller.toRoman(1);

        Map<String, Object> result = response.getBody();

        assertNotNull(result);
        assertEquals("1", result.get("input"));
        assertEquals("I", result.get("output"));

        System.out.println("======================================");
        System.out.println("TEST PASSED: Integer 1 → Roman I");
        System.out.println("======================================");
    }
}
