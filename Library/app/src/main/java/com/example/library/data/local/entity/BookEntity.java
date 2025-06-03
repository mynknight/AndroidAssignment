package com.example.library.data.local.entity;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "books")
public class BookEntity {
    @PrimaryKey
    @NonNull
    public String id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "author")
    public String author;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "rating")
    public float rating;

    @ColumnInfo(name = "image_url")
    public String imageUrl;

    @ColumnInfo(name = "is_bookmarked")
    public boolean isBookmarked;

    // Required empty constructor for Room
    public BookEntity() {}

    public BookEntity(@NonNull String id, String title, String author,
                      String description, float rating, String imageUrl,
                      boolean isBookmarked) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.isBookmarked = isBookmarked;
    }
}
