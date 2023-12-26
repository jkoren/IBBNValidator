package com.virtualpairprogrammers.isbntools;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class StockManagementTests {
    // declare at class level ...
    ExternalISBNDataService testWebService;
    ExternalISBNDataService testDatabaseService;
    StockManager stockManager;
    @BeforeEach
    public void setup() {
        // ... but instantiate at set up
        System.out.println("set up running");
        testWebService = mock(ExternalISBNDataService.class);
        StockManager stockManager = new StockManager();
        testDatabaseService = mock(ExternalISBNDataService.class);
        stockManager.setDatabaseService(testDatabaseService);
        stockManager.setWebService(testWebService);
    }
    @AfterEach
    public void teardown() {
        System.out.println("tear-down running");
    }
//    @Test
//    public void testCanGetACorrectLocatorCode() {
//        when(testWebService.lookup(anyString())).thenReturn(new Book("0140177396", "Of Mice and Men", "J. Steinbeck"));
//        when(testDatabaseService.lookup(anyString())).thenReturn(null);
//
//        String isbn = "0140177396";
//        String locatorCode = stockManager.getLocatorCode(isbn);
//        // check to see if the locator code we get back is what we think it should be
//        assertEquals("7396J4", locatorCode);
//    }

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
