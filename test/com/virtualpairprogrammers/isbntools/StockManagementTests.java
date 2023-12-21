package com.virtualpairprogrammers.isbntools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StockManagementTests {
    @Test
    public void testCanGetACorrectLocatorCode() {
//        fail("not yet implemented");
        String isbn = "9140177396";
        StockManager stockManager = new StockManager();
        String locatorCode = stockManager.getLocatorCode(isbn);
        // check to see if the locator code we get back is what we think it should be
        assertEquals("7396J4", locatorCode);


    }
}
