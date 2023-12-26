package com.virtualpairprogrammers.isbntools;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StockManagementTests {
    @Test
    public void testCanGetACorrectLocatorCode() {

        ExternalISBNDataService testDatabaseService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                // for the purposes of this test, .lookup should always return null
                return null;
            }
        };
        ExternalISBNDataService testWebService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return new Book(isbn, "Of Mice and Men", "J. Steinbeck");
            }
        };

        StockManager stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDatabaseService);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);
        // check to see if the locator code we get back is what we think it should be
        assertEquals("7396J4", locatorCode);


    }

//    @Test
//    public void databaseIsUsedIfDataIsPresent() {
//        fail();
//    }
//    @Test
//    public void webserviceIsUsedIfDataIsNotPresentInDatabase() {
//        fail();
//    }
}
