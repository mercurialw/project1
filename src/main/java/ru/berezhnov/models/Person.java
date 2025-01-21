package ru.berezhnov.models;

import jakarta.validation.constraints.*;

public class Person {

    private int id;

    @Size(min = 10, max = 100, message = "Full name should be between 10 and 100 characters")
    private String fullName;

    @Max(value = 2025, message = "Birth year shouldn't be above 2024")
    @Min(value = 1899, message = "Birth year shouldn't be under 1900")
    private int birthYear;

    public Person(int id, String fullName, int birthYear) {
        this.id = id;
        this.fullName = fullName;
        this.birthYear = birthYear;
    }

    public Person() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
