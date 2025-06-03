package com.example.library.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;
import com.example.library.domain.model.Book;
import com.example.library.ui.home.adapter.BookAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements BookAdapter.OnBookActionListener {

    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private BookViewModel viewModel; // Make sure this matches your ViewModel

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(BookViewModel.class);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter = new BookAdapter(new ArrayList<>());
        adapter.setOnBookActionListener(this);
        recyclerView.setAdapter(adapter);

        viewModel.getBooks().observe(getViewLifecycleOwner(), books -> {
            if (books != null) {
                adapter.setBooks(books);
            }
        });
    }

    // Implement the required method from OnBookActionListener
    @Override
    public void onBookmarkToggle(Book book, int position, boolean newState) {
        viewModel.toggleBookmark(book, newState);
    }
}
