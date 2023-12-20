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
        assertTrue(result, "second value");
    }
    @Test
    public void checkAnInvalidISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449117");
        assertFalse(result);
    }

    @Test
    public  void nineDigitISBNsAreNotAllowed() {
        ValidateISBN validator = new ValidateISBN();
        // JUnit 5 approach
        assertThrows(NumberFormatException.class,
                () -> {
                validator.checkISBN("123456789");
                // here we don't care about the result, only that we get an exception on improperly formatted number
                // boolean result = validator.checkISBN("123456789");

                }
        );
    }
}
