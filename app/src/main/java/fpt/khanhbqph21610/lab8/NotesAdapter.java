package fpt.khanhbqph21610.lab8;

import android.app.AlertDialog;
import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {
    private List<Note> noteList;
    private Context context;
    private DatabaseHelper databaseHelper;

    public NotesAdapter(List<Note> noteList, Context context) {
        this.noteList = noteList;
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.noteContent.setText(note.getContent());
        holder.noteTime.setText(note.getTime());

        // Set up the delete button (X icon)
        holder.deleteButton.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Confirm Delete")
                    .setMessage("Are you sure you want to delete this note?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        databaseHelper.deleteNote(note.getId());
                        noteList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, noteList.size());
                        Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });

        // Set up the item click listener to show the detail dialog
        holder.itemView.setOnClickListener(v -> {
            NoteDetailDialogFragment dialogFragment = NoteDetailDialogFragment.newInstance(note.getContent(), note.getTime());
            dialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), "noteDetail");
        });
    }


    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView noteContent, noteTime;
        ImageButton deleteButton; // Assuming you're using an ImageButton for the delete button

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            noteContent = itemView.findViewById(R.id.noteContent);
            noteTime = itemView.findViewById(R.id.noteTime);
            deleteButton = itemView.findViewById(R.id.deleteButton); // Reference to the delete button
        }
    }
}


