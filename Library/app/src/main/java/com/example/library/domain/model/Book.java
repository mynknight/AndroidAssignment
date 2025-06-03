package com.example.library.domain.model;


public class Book {
    private final String id;
    private final String title;
    private final String author;
    private final String description;
    private final float rating;
    private final String imageUrl;
    private boolean isBookmarked;

    public Book(String id, String title, String author,
                String description, float rating,
                String imageUrl, boolean isBookmarked) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.isBookmarked = isBookmarked;
    }
    public void setBookmarked(boolean bookmarked) {
        this.isBookmarked = bookmarked;
    }
    public Book withBookmarked(boolean bookmarked) {
        return new Book(
                this.id,
                this.title,
                this.author,
                this.description,
                this.rating,
                this.imageUrl,
                bookmarked
        );}
    // Getters (no setters for immutability)
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getDescription() { return description; }
    public float getRating() { return rating; }
    public String getImageUrl() { return imageUrl; }
    public boolean isBookmarked() { return isBookmarked; }
}
