package com.virtualpairprogrammers.isbntools;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

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

    @Test
    public void databaseIsUsedIfDataIsPresent() {
        // mocking a service to get a book from the database
        // mockito creates a dummy class that is an implementation of this interface, and we can call its methods
        // mockito returns a book opbject, but author is null and title is null
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

        // mockito when.then
        when(databaseService.lookup("0140177396")).thenReturn(new Book("0140177396","abc", "abc"));

        StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);
        // check to see if the locator code we get back is what we think it should be
//        assertEquals("7396J4", locatorCode);

        // here what we care about, was the right service called?
        verify(databaseService, times(1)).lookup("0140177396");
        verify(webService, times(0)).lookup(anyString());
    }
    @Test
    public void webserviceIsUsedIfDataIsNotPresentInDatabase() {
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

        // mockito when.then
        when(databaseService.lookup("0140177396")).thenReturn(null);
        when(webService.lookup("0140177396")).thenReturn(new Book("0140177396","abc", "abc"));

        StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);
        // check to see if the locator code we get back is what we think it should be
//        assertEquals("7396J4", locatorCode);

        // here what we care about, was the right service called?
        verify(databaseService, times(1)).lookup("0140177396");
        verify(webService, times(1)).lookup("0140177396");
    }
}
