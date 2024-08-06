package fpt.khanhbqph21610.lab8;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddNoteFragment extends Fragment {
    private EditText noteContent, noteTime;
    private Button saveButton;
    private DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_note, container, false);

        noteContent = view.findViewById(R.id.noteContent);
        noteTime = view.findViewById(R.id.noteTime);
        saveButton = view.findViewById(R.id.saveButton);

        // Initialize the DatabaseHelper
        databaseHelper = new DatabaseHelper(getActivity());

        saveButton.setOnClickListener(v -> {
            if (noteContent.getText().toString().isEmpty() || noteTime.getText().toString().isEmpty()) {
                Toast.makeText(getActivity(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Create a new Note object
                Note note = new Note(noteContent.getText().toString(), noteTime.getText().toString());

                // Save the note to the database
                databaseHelper.addNote(note);

                // Clear the input fields
                noteContent.setText("");
                noteTime.setText("");

                // Show a success message
                Toast.makeText(getActivity(), "Note saved successfully", Toast.LENGTH_SHORT).show();

                // Update the ListFragment
                ViewPager viewPager = getActivity().findViewById(R.id.viewPager);
                ViewPagerAdapter adapter = (ViewPagerAdapter) viewPager.getAdapter();
                ListFragment listFragment = (ListFragment) adapter.getItem(0);
                listFragment.updateNotes();

                // Switch to ListFragment (optional, if you want to automatically switch tabs)
                viewPager.setCurrentItem(0);
            }
        });



        return view;
    }
}
