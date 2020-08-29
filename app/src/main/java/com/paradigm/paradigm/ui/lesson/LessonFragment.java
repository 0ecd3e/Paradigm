package com.paradigm.paradigm.ui.lesson;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.paradigm.paradigm.MainActivity;
import com.paradigm.paradigm.R;

public class LessonFragment extends Fragment {

    private LessonViewModel mViewModel;

    public static LessonFragment newInstance() {
        return new LessonFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lesson, container, false);

        TextView lessonTitle = root.findViewById(R.id.lessonTitle);
        TextView lessonText = root.findViewById(R.id.lessonText);

        lessonTitle.setText(MainActivity.getCurrentLesson().getName());
        lessonText.setText(MainActivity.getCurrentLesson().getLessonContent());

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LessonViewModel.class);
        // TODO: Use the ViewModel
    }

    //find way to set question buttons green if answered correctly

}
