package fpt.khanhbqph21610.lab8;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class NoteDetailDialogFragment extends DialogFragment {
    private static final String ARG_CONTENT = "content";
    private static final String ARG_TIME = "time";

    public static NoteDetailDialogFragment newInstance(String content, String time) {
        NoteDetailDialogFragment fragment = new NoteDetailDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CONTENT, content);
        args.putString(ARG_TIME, time);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_note_detail, null);

        TextView contentTextView = view.findViewById(R.id.noteContent);
        TextView timeTextView = view.findViewById(R.id.noteTime);

        if (getArguments() != null) {
            contentTextView.setText(getArguments().getString(ARG_CONTENT));
            timeTextView.setText(getArguments().getString(ARG_TIME));
        }

        builder.setView(view)
                .setPositiveButton("OK", (dialog, id) -> dialog.dismiss());

        return builder.create();
    }
}
