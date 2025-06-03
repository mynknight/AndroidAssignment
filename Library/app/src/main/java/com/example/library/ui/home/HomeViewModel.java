package com.example.library.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.library.domain.repository.BookRepository;
import com.example.library.data.repository.BookRepositoryImpl;
import com.example.library.domain.model.Book;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private final BookRepository repository;
    private final LiveData<List<Book>> books;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        repository = new BookRepositoryImpl(application);
        books = repository.getBooks();
    }

    public LiveData<List<Book>> getBooks() {

        return books;
    }

    public void toggleBookmark(Book book) {
        Book updatedBook = new Book(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getDescription(),
                book.getRating(),
                book.getImageUrl(),
                !book.isBookmarked() // Toggle bookmark status
        );
        repository.bookmarkBook(updatedBook);
    }
}
