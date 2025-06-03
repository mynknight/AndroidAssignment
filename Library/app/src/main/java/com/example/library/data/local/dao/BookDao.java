package com.example.library.data.local.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.library.data.local.entity.BookEntity;

import java.util.List;

@Dao
public interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<BookEntity> books);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BookEntity book);

    @Update
    void update(BookEntity book);

    @Query("SELECT * FROM books")
    LiveData<List<BookEntity>> getAllBooks();

    @Query("SELECT * FROM books WHERE id = :bookId LIMIT 1")
    LiveData<BookEntity> getBookById(String bookId);

    @Query("SELECT * FROM books WHERE is_bookmarked = 1")
    LiveData<List<BookEntity>> getBookmarkedBooks();

    // To bookmark/unbookmark a book
    @Query("UPDATE books SET is_bookmarked = :isBookmarked WHERE id = :bookId")
    void updateBookmark(String bookId, boolean isBookmarked);

    @Query("DELETE FROM books")
    void deleteAll();
}
