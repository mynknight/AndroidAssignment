package com.example.library.ui.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;
import com.example.library.domain.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Book> books = new ArrayList<>();
    private int expandedPosition = -1; // No card expanded by default
    private OnBookActionListener listener;

    public interface OnBookActionListener {
        void onBookmarkToggle(Book book, int position, boolean newState);
    }

    public BookAdapter(List<Book> books) {
        this.books = books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public void setOnBookActionListener(OnBookActionListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book_card, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);

        // Bind data
        holder.bookTitle.setText(book.getTitle());
        holder.bookAuthor.setText(book.getAuthor());
        holder.bookDescription.setText(book.getDescription());

        try {
            int resourceId = Integer.parseInt(book.getImageUrl());
            holder.bookImage.setImageResource(resourceId);
        } catch (NumberFormatException e) {
            holder.bookImage.setImageResource(R.drawable.book_placeholder);
        }

        boolean isExpanded = position == expandedPosition;
        holder.bookDescription.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.downloadButton.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.itemView.setActivated(isExpanded);
        holder.bookRating.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.bookRating.setRating(book.getRating());

        holder.itemView.setOnClickListener(v -> {
            int oldPosition = expandedPosition;
            expandedPosition = isExpanded ? -1 : position;
            if (oldPosition != -1) notifyItemChanged(oldPosition);
            notifyItemChanged(position);
        });

        holder.downloadButton.setOnClickListener(v -> {
            Toast.makeText(v.getContext(),
                    "Downloading: " + book.getTitle(),
                    Toast.LENGTH_SHORT).show();
        });

        // Set bookmark button text
        holder.actionButton.setText(book.isBookmarked() ? "Bookmarked" : "Bookmark");

        // Bookmark button click listener
        holder.actionButton.setOnClickListener(v -> {
            boolean newState = !book.isBookmarked();
            book.setBookmarked(newState);
            holder.actionButton.setText(newState ? "Bookmarked" : "Bookmark");
            if (listener != null) {
                listener.onBookmarkToggle(book, position, newState);
            }
        });
    }


    @Override
    public int getItemCount() {
        return books.size();
    }

    // ViewHolder
    public static class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImage;
        TextView bookTitle, bookAuthor, bookDescription;
        RatingBar bookRating;

        Button actionButton, downloadButton;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.book_image);
            bookTitle = itemView.findViewById(R.id.book_title);
            bookAuthor = itemView.findViewById(R.id.book_author);
            bookRating = itemView.findViewById(R.id.book_rating);
            bookDescription = itemView.findViewById(R.id.book_description);
            actionButton = itemView.findViewById(R.id.action_button);
            downloadButton = itemView.findViewById(R.id.download_button);
        }
    }

    // Listener interface
//    public interface OnBookActionListener {
//        void onBookClick(Book book, int position);
//        void onBookButtonClick(Book book, int position);
//    }
}
