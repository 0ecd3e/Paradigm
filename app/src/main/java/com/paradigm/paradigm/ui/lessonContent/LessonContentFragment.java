package com.paradigm.paradigm.ui.lessonContent;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.paradigm.paradigm.R;

public class LessonContentFragment extends Fragment {

    private LessonContentViewModel mViewModel;

    public static LessonContentFragment newInstance() {
        return new LessonContentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.lesson_content_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LessonContentViewModel.class);
        // TODO: Use the ViewModel
    }

}
