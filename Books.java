package com.example.librarymanagementsystem;

import javafx.beans.property.SimpleStringProperty;

public class Books {
    private final int status;
    private final String title;
    private final String genre;
    private final String author;
    private final String description;

    public Books(int status, String title,
                 String genre, String author, String description) {
        super();
        this.status = status;
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }
}
