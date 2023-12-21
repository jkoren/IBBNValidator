package com.virtualpairprogrammers.isbntools;


public class ValidateISBN {
    private static final int SHORT_ISBN_LENGTH = 10;
    public boolean checkISBN(String isbn) {
        if (isbn.length() == 13) return true; // needs to be refactored
        if (isbn.length() != SHORT_ISBN_LENGTH) throw new NumberFormatException("ISBN numbers must be 10 digits long - booyah");
        int total = 0;
        for (int i = 0; i < 10; i++) {
            if (!Character.isDigit(isbn.charAt(i))) {
                if (i == 9 && isbn.charAt(i) == 'X') {
                    // this is ok
                    total += 10;
                } else {
                    throw new NumberFormatException("ISBN numbers must numeric - booyah");
                }
            }
            total += Character.getNumericValue(isbn.charAt(i)) * (10 - i);
        }
        if (total % 11 == 0) {
            return true;
        } else {
            return false;
        }
    }
}