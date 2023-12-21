package com.virtualpairprogrammers.isbntools;

public interface ExternalISBNDataService {
    // because this is an interface,
    // this just means that any ExternalISBNDataService must have a lookup method
    public Book lookup(String isbn);
}
