package com.example.library.data.mapper;
import com.example.library.data.local.entity.BookEntity;
import com.example.library.domain.model.Book;

import java.util.ArrayList;
import java.util.List;

// data/mapper/BookMapper.java
public class BookMapper {
    public static List<Book> toDomainList(List<BookEntity> entities) {
        List<Book> books = new ArrayList<>();
        for (BookEntity entity : entities) {
            books.add(toDomain(entity));
        }
        return books;
    }

    public static Book toDomain(BookEntity entity) {
        return new Book(
                entity.id,
                entity.title,
                entity.author,
                entity.description,
                entity.rating,
                entity.imageUrl,
                entity.isBookmarked
        );
    }

    public static BookEntity toEntity(Book book) {
        return new BookEntity(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getDescription(),
                book.getRating(),
                book.getImageUrl(),
                book.isBookmarked()
        );
    }
}
