package com.example.mycalculator;

import static org.junit.Assert.*;
import org.junit.Test;

public class EvaluateTest {

    // fail evaluate_add
    @Test
    public void evaluate_add() {
        Evaluate e = new Evaluate();
        double actual = e.evaluate("2+2").doubleValue();
        double expected = 5.0;
        assertEquals("We have an error, values don't match", expected, actual, 0.0001);
    }

    // success display
    @Test
    public void clearDisplay() {
        String text_display = "11111";
        text_display = "";
        assertEquals("", text_display);
    }

    // success evaluate_dd
    @Test
    public void evaluate_add_success() {
        Evaluate e = new Evaluate();
        double actual = e.evaluate("2+2").doubleValue();
        double expected = 4.0; // correct value
        assertEquals("Addition works correctly", expected, actual, 0.0001);
    }

    // fail display
    @Test
    public void clearDisplay_fail() {
        String text_display = "12345"; // not cleared on purpose
        assertEquals("Display should be empty", "", text_display);
    }
}
