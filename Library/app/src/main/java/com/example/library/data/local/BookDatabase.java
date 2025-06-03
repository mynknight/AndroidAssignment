package com.example.library.data.local;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.library.data.local.dao.BookDao;
import com.example.library.data.local.entity.BookEntity;

@Database(entities = {BookEntity.class}, version = 1, exportSchema = false)
public abstract class BookDatabase extends RoomDatabase {
    private static volatile BookDatabase INSTANCE;

    public abstract BookDao bookDao();

    public static BookDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (BookDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            BookDatabase.class, "book_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
