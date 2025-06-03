package com.example.library.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.domain.model.Book;
import com.example.library.ui.home.adapter.BookAdapter;
import com.example.library.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements BookAdapter.OnBookActionListener {

    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private ArrayList<Book> bookList;

    private HomeViewModel viewModel;

    private void setupAdapter() {
        adapter = new BookAdapter(new ArrayList<>()); // Start with empty list
        adapter.setOnBookActionListener(this);
        recyclerView.setAdapter(adapter);
    }
    @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        setupRecyclerView();
        setupAdapter();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
//        Log.d("HomeFragment", "Books received: " + books.size());
        viewModel.getBooks().observe(getViewLifecycleOwner(), books -> {
            if (books != null) adapter.setBooks(books);
        });
    }

    private void setupRecyclerView() {
        // Set vertical layout manager for vertical scrolling
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        recyclerView.setLayoutManager(layoutManager);
    }


    @Override
    public void onBookClick(Book book, int position) {
//        Bundle args = new Bundle();
//        args.putSerializable("book", book);
//        Navigation.findNavController(requireView())
//                .navigate(R.id.action_home_to_detail, args);
    }


    @Override
    public void onBookButtonClick(Book book, int position) {
        Toast.makeText(getContext(),
                "Button clicked for: " + book.getTitle(),
                Toast.LENGTH_SHORT).show();
        handleBookAction(book, position);
    }

    private void handleBookAction(Book book, int position) {
        switch (position % 3) {
            case 0:
                showMessage("Added '" + book.getTitle() + "' to favorites");
                break;
            case 1:
                showMessage("Added '" + book.getTitle() + "' to reading list");
                break;
            case 2:
                showMessage("Sharing '" + book.getTitle() + "'");
                break;
        }
    }

    private void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

}
