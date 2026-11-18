package com.mycompany.productapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ProductCalculationsTest {

    //Test 1
    @Test
    public void GetVat_WithValidData_ReturnsCorrectValue() {
        // Let's test with a price of 100. 15% of 100 is 15.
        ProductCalculations calc = new ProductCalculations(100.00);
        
        // Delta of 0.001 handles floating point precision
        assertEquals(15.00, calc.GetVat(), 0.001);
    }

    // Test 2
    @Test
    public void GetTotal_WithValidData_ReturnsCorrectValue() {
        // Price = 100. 
        // VAT = 15. Commission = 5.
        // Total = 100 + 15 + 5 = 120.
        ProductCalculations calc = new ProductCalculations(100.00);
        
        assertEquals(120.00, calc.GetTotal(), 0.001);
    }
}