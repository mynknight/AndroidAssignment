package com.example.library.ui.favourite;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;
import com.example.library.ui.home.adapter.BookAdapter;

import java.util.ArrayList;
public class FavouritesFragment extends Fragment {
    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private FavouritesViewModel viewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new BookAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(FavouritesViewModel.class);
        viewModel.getBookmarkedBooks().observe(getViewLifecycleOwner(), books -> {
            adapter.setBooks(books);
        });
    }
}
