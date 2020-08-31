package com.paradigm.paradigm.ui.question;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.paradigm.paradigm.MainActivity;
import com.paradigm.paradigm.R;
import com.paradigm.paradigm.exercises.question.Question;
import com.paradigm.paradigm.profile.UserProgress;
import com.paradigm.paradigm.profile.progressEntries.QuestionProgress;

public class MCQFragment extends Fragment {

    private MCQViewModel mViewModel;
    View root;

    public static MCQFragment newInstance() {
        return new MCQFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.m_c_q_fragment, container, false);
        updateQuestionProgress(root);

        TextView questionTitle = root.findViewById(R.id.MCQTitle);
        questionTitle.setText(MainActivity.getCurrentQuestion().getQuestionName());
        TextView questionText = root.findViewById(R.id.MCQContent);
        questionText.setText(MainActivity.getCurrentQuestion().getQuestionText());

        CardView cardView = root.findViewById(R.id.checkMCQAnswer);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonClicked(root.findViewById(R.id.MCQChoices));
            }
        });

        return root;
    }

    private void updateQuestionProgress(View root) {
        UserProgress userProgress = ((MainActivity) requireActivity()).getUserProfile().getUserProgress();
        QuestionProgress questionProgress = userProgress.getQuestionProgress(MainActivity.getCurrentQuestion());
        if (questionProgress.isComplete()) {
            TextView text = root.findViewById(R.id.MCQCompleteText);
            String answerKey = "You have correctly answered this question before.\n" + "Correct answer: " + MainActivity.getCurrentQuestion().getAnswer().getAnswer();
            text.setText(answerKey);
            CardView indicator = root.findViewById(R.id.MCQCompleteIndicator);
            indicator.setCardBackgroundColor(0xff00ff00);
        }
        userProgress.isLessonComplete(MainActivity.getCurrentLesson());
        ((MainActivity) requireActivity()).saveProgress();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MCQViewModel.class);
        // TODO: Use the ViewModel
    }

    public void onRadioButtonClicked(View view) {
        Question question = MainActivity.getCurrentQuestion();
        RadioGroup radioGroup = (RadioGroup) view;
        RadioButton selection = (RadioButton) radioGroup.findViewById(
                radioGroup.getCheckedRadioButtonId());
        if (selection != null) {
            String selectedText = (String) selection.getText();
            handleAnswer(question, selectedText.substring(0, 1));
        }
    }

    private void handleAnswer(Question question, String choice) {
        question.checkAnswer(choice, question.getAnswer(),
                ((MainActivity) requireActivity()).getUserProfile().getUserProgress());
        updateQuestionProgress(root);
    }

}
