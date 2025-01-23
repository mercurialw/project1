package ru.berezhnov.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

import java.util.Optional;

public class Book {

    private int id;

    @NotEmpty(message = "Name should not be empty")
    private String title;

    @NotEmpty(message = "Author name should not be empty")
    @Size(min = 10, max = 100, message = "Full name should be between 10 and 100 characters")
    private String author;

    @NotEmpty(message = "Year should not be empty")
    @Max(value = 2026, message = "Birth year shouldn't be above 2025")
    private int year;

    private Optional<Integer> borrowerId;

    public Book(int id, String title, String author, int year, Integer borrowerId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.borrowerId = Optional.of(borrowerId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Integer getBorrowerId() {
        return borrowerId.get();
    }

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = Optional.of(borrowerId);
    }
}
