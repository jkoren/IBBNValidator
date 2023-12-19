package com.virtualpairprogrammers.isbntools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ValidateISBNTest {
    @Test
    public void checkAValidISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449116");
//        assertTrue(result); // this works, but can add text to be descriptive if we want)
        assertTrue(result, "first value");
        result = validator.checkISBN("0140177396");
//        assertTrue(result); // this works, but can add text to be descriptive if we want)
        assertTrue(result, "second value");
    }
    @Test
    public void checkAnInvalidISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449117");
        assertFalse(result);
    }
}
