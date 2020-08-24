package com.paradigm.paradigm.ui.checkpoint;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.paradigm.paradigm.R;

public class CheckpointFragment extends Fragment {

    private CheckpointViewModel checkpointViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        checkpointViewModel =
                ViewModelProviders.of(this).get(CheckpointViewModel.class);
        View root = inflater.inflate(R.layout.fragment_checkpoint, container, false);
        final TextView textView = root.findViewById(R.id.nav_checkpoint);
        checkpointViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
