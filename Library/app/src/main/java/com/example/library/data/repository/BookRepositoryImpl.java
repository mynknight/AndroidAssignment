package com.example.library.data.repository;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.library.data.local.BookDatabase;
import com.example.library.data.local.dao.BookDao;
import com.example.library.data.local.entity.BookEntity;
import com.example.library.data.mapper.BookMapper;
import com.example.library.domain.model.Book;
import com.example.library.domain.repository.BookRepository;
import com.example.library.utils.AppExecutors;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

// data/repository/BookRepositoryImpl.java
public class BookRepositoryImpl implements BookRepository {
    private final BookDao bookDao;
    private final Context context;
    private final AppExecutors executors;


    public BookRepositoryImpl(Application application) {
        BookDatabase database = BookDatabase.getInstance(application);
        bookDao = database.bookDao();
        context = application.getApplicationContext();
        executors = AppExecutors.getInstance();

        loadInitialBooksIfNeeded();
    }
    @Override
    public LiveData<List<Book>> getBookmarkedBooks() {
        return Transformations.map(bookDao.getBookmarkedBooks(), entities -> {
            List<Book> books = new ArrayList<>();
            for (BookEntity entity : entities) {
                books.add(BookMapper.toDomain(entity));
            }
            return books;
        });
    }

    private void loadInitialBooksIfNeeded() {

//        executors.diskIO().execute(() -> {
//            try{
//            if (bookDao.getCount() == 0) { // Add this method in BookDao
//                loadInitialBooks();
//            }
////            Log.d("BookRepo", "Loaded " + bookEntities.size() + " books");
//        } catch (Exception e) {
//            Log.e("BookRepo", "Error loading books", e);
//            e.printStackTrace();
//        }
//        });

        executors.diskIO().execute(this::loadInitialBooks);
    }
//        private void loadInitialBooks() {
//            try {
//                InputStream is = context.getAssets().open("books.json");
//                int size = is.available();
//                byte[] buffer = new byte[size];
//                is.read(buffer);
//                is.close();
//                String json = new String(buffer, "UTF-8");
//
//                JSONArray jsonArray = new JSONArray(json);
//                List<BookEntity> bookEntities = new ArrayList<>();
//
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    JSONObject obj = jsonArray.getJSONObject(i);
//                    BookEntity book = new BookEntity(
//                            obj.getString("id"),
//                            obj.getString("title"),
//                            obj.getString("author"),
//                            obj.getString("description"),
//                            (float) obj.getDouble("rating"),
//                            obj.getString("imageUrl"),
//                            false
//                    );
//                    bookEntities.add(book);
//                }
//                bookDao.insertAll(bookEntities);
//                Log.d("BookRepo", "Inserted " + bookEntities.size() + " books into DB");
//            } catch (Exception e) {
//                Log.e("BookRepo", "Failed to load initial books", e);
//            }
//        }

    @Override
    public LiveData<List<Book>> getBooks() {
        return Transformations.map(bookDao.getAllBooks(), BookMapper::toDomainList);
    }

    @Override
    public void bookmarkBook(Book book) {
        AppExecutors.getInstance().diskIO().execute(() -> {
            bookDao.update(BookMapper.toEntity(book));
        });
    }

    @Override
    public LiveData<Book> getBookById(String id) {
        return Transformations.map(bookDao.getBookById(id), BookMapper::toDomain);
    }

    // Fake API simulation
// In BookRepositoryImpl.java

    private void loadInitialBooks() {
        // Run on background thread
        AppExecutors.getInstance().diskIO().execute(() -> {
            try {
                InputStream is = context.getAssets().open("books.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                String json = new String(buffer, "UTF-8");

                JSONArray jsonArray = new JSONArray(json);
                List<BookEntity> bookEntities = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    BookEntity book = new BookEntity(
                            obj.getString("id"),
                            obj.getString("title"),
                            obj.getString("author"),
                            obj.getString("description"),
                            (float) obj.getDouble("rating"),
                            obj.getString("imageUrl"),
                            false // default isBookmarked
                    );
                    bookEntities.add(book);
                }

                bookDao.insertAll(bookEntities);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
