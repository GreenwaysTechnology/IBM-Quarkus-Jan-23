package org.acme.service.entity;

public class Book {
    private String title;
    private String Isbn;
    private String author;

    public Book() {
    }

    public Book(String title, String isbn, String author) {
        this.title = title;
        Isbn = isbn;
        this.author = author;
    }
     public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return Isbn;
    }

    public void setIsbn(String isbn) {
        Isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
