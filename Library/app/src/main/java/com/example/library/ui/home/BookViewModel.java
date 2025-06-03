package com.example.library.ui.home;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import android.app.Application;
import com.example.library.domain.model.Book;
import com.example.library.domain.repository.BookRepository;
import com.example.library.data.repository.BookRepositoryImpl;

import java.util.List;

public class BookViewModel extends AndroidViewModel {
    private final BookRepository repository;
    private final LiveData<List<Book>> books;

    public BookViewModel(Application application) {
        super(application);
        repository = new BookRepositoryImpl(application);
        books = repository.getBooks(); // This should return LiveData<List<Book>>
    }

    public LiveData<List<Book>> getBooks() {
        return books;
    }

    public void toggleBookmark(Book book, boolean newState) {
        book.setBookmarked(newState);
        repository.bookmarkBook(book);
    }
}
