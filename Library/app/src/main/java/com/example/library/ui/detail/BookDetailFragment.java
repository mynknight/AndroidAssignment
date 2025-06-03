package com.example.library.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.library.R;
import com.example.library.domain.model.Book;

public class BookDetailFragment extends Fragment {

    private Book book;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get book from arguments
        if (getArguments() != null) {
            book = (Book) getArguments().getSerializable("book");
        }

        // Bind data to views
        ImageView bookImage = view.findViewById(R.id.book_image);
        TextView bookTitle = view.findViewById(R.id.book_title);
        TextView bookAuthor = view.findViewById(R.id.book_author);
        TextView bookDescription = view.findViewById(R.id.book_description);
//        Button btnBookmark = view.findViewById(R.id.btn_bookmark);
//        Button btnDownload = view.findViewById(R.id.btn_download);

        if (book != null) {
            bookTitle.setText(book.getTitle());
            bookAuthor.setText(book.getAuthor());
            bookDescription.setText(book.getDescription());

            // Placeholder image logic
            try {
                int resourceId = Integer.parseInt(book.getImageUrl());
                bookImage.setImageResource(resourceId);
            } catch (Exception e) {
                bookImage.setImageResource(R.drawable.book_placeholder);
            }

//            btnBookmark.setText(book.isBookmarked() ? "Unbookmark" : "Bookmark");
//            btnBookmark.setOnClickListener(v -> {
//                // For MVP, just show a toast
//                Toast.makeText(requireContext(),
//                        book.isBookmarked() ? "Removed from bookmarks" : "Added to bookmarks",
//                        Toast.LENGTH_SHORT).show();
//                // You can add actual DB update logic here if needed
//
//
//            });
//            // Download button logic (MVP: just show a Toast)
//            // Download button logic (MVP: just show a Toast)
//            btnDownload.setOnClickListener(v -> {
//                Toast.makeText(requireContext(),
//                        "Download started for: " + book.getTitle(),
//                        Toast.LENGTH_SHORT).show();
//                // You can add actual download logic here later
//            });
        }
    }
}
