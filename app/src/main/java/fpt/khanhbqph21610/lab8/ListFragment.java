package fpt.khanhbqph21610.lab8;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class ListFragment extends Fragment {
    private RecyclerView recyclerView;
    private NotesAdapter adapter;
    private List<Note> noteList = new ArrayList<>();
    private DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new NotesAdapter(noteList, getActivity());
        recyclerView.setAdapter(adapter);

        databaseHelper = new DatabaseHelper(getActivity());

        loadNotes();  // Load notes when the fragment is created

        return view;
    }


    private void loadNotes() {
        noteList.clear();  // Clear the existing list to avoid duplicates
        noteList.addAll(databaseHelper.getAllNotes());  // Fetch all notes from the database
        adapter.notifyDataSetChanged();  // Notify the adapter that the data has changed
    }

    public void updateNotes() {
        loadNotes();  // Reload the notes and refresh the RecyclerView
    }
}


