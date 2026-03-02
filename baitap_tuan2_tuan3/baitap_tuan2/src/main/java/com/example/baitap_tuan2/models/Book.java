package com.example.baitap_tuan2.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Book {
    @NotBlank(message = "Title is required")
    String title;
    @NotBlank(message = "Author is required")
    String author;
    String id;
    @Min(value = 0, message = "Price must be positive")
    double price;
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // constructor
    public Book(String title, String author, String id, double price, String url) {
        this.title = title;
        this.author = author;
        this.id = id;
        this.price = price;
        this.url = url;
    }

    // toString
    @Override
    public String toString() {
        return "Book [title=" + title + ", author=" + author + ", id=" + id + ", price=" + price + ", url=" + url + "]";
    }
}
