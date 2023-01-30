package org.acme.service;

import org.acme.service.entity.Book;
import org.acme.service.interfaces.NumberGenerator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class BookService {
    //dependency type
    @Inject
    NumberGenerator numberGenerator;
    public Book createBook() {
        Book book = new Book("Quarkus In Action", "000", "Subramanian");
        book.setIsbn(numberGenerator.generateISBNNumber());
        return book;
    }
}
