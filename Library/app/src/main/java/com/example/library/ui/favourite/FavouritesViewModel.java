package com.example.library.ui.favourite;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.library.data.repository.BookRepositoryImpl;
import com.example.library.domain.model.Book;
import com.example.library.domain.repository.BookRepository;

import java.util.List;

public class FavouritesViewModel extends AndroidViewModel {
    private final BookRepository repository;
    private final LiveData<List<Book>> bookmarkedBooks;

    public FavouritesViewModel(@NonNull Application application) {
        super(application);
        repository = new BookRepositoryImpl(application);
        bookmarkedBooks = repository.getBookmarkedBooks();
    }

    public LiveData<List<Book>> getBookmarkedBooks() {
        return bookmarkedBooks;
    }
}
