package com.virtualpairprogrammers.isbntools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ValidateISBNTest {
    @Test
    public void checkAValid10DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449116");
//        assertTrue(result); // this works, but can add text to be descriptive if we want)
        assertTrue(result, "first value");
        result = validator.checkISBN("0140177396");
        assertTrue(result, "second value");
    }
    @Test
    public void checkAValid13DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("9780684833392");
        assertTrue(result, "13 digit");
    }
    @Test
    public void checkAnInvalid10DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449117");
        assertFalse(result);
    }

//    @Test
//    public void checkAnInvalid13DigitISBN() {
//        ValidateISBN validator = new ValidateISBN();
//        boolean result = validator.checkISBN("9780684833393");
//        assertFalse(result); // the ValidateISBN method has not been adjusted for this yet
//    }

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
    @Test
    public  void charactersAreNotAllowed() {
        ValidateISBN validator = new ValidateISBN();
        // JUnit 5 approach
        assertThrows(NumberFormatException.class,
            () -> {
            validator.checkISBN("helloworld");
            // here we don't care about the result, only that we get an exception on improperly formatted number
            // boolean result = validator.checkISBN("123456789");
            }
        );
    }

    @Test
    public void ISBNCanEndInX() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("012000030X");
        assertTrue(result);
    }
    @Test
    public void ISBNCanNotEndInY() {
        ValidateISBN validator = new ValidateISBN();
        assertThrows(NumberFormatException.class,
                () -> {
                    validator.checkISBN("012000030Y");
                    // here we don't care about the result, only that we get an exception on improperly formatted number
                    // boolean result = validator.checkISBN("123456789");
                }
        );
    }
}
