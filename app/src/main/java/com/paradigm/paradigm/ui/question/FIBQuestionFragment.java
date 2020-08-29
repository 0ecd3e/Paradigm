package com.paradigm.paradigm.ui.question;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.paradigm.paradigm.R;

public class FIBQuestionFragment extends Fragment {

    private FIBQuestionViewModel mViewModel;

    public static FIBQuestionFragment newInstance() {
        return new FIBQuestionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f_i_b_question_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FIBQuestionViewModel.class);
        // TODO: Use the ViewModel
    }

    public void fibToast(View view) {
        Toast.makeText(requireActivity(), "FIBTOAST", Toast.LENGTH_SHORT).show();
    }

}
