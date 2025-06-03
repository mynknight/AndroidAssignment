package com.example.library.domain.repository;

import androidx.lifecycle.LiveData;

import com.example.library.domain.model.Book;

import java.util.List;

public interface BookRepository {
    LiveData<List<Book>> getBooks();
    LiveData<List<Book>> getBookmarkedBooks();

    void bookmarkBook(Book book);
    LiveData<Book> getBookById(String id);
}