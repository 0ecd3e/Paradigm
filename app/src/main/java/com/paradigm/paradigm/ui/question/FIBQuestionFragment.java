package com.paradigm.paradigm.ui.question;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.paradigm.paradigm.MainActivity;
import com.paradigm.paradigm.R;
import com.paradigm.paradigm.exercises.question.Question;
import com.paradigm.paradigm.profile.UserProgress;
import com.paradigm.paradigm.profile.progressEntries.CourseProgress;
import com.paradigm.paradigm.profile.progressEntries.ModuleProgress;
import com.paradigm.paradigm.profile.progressEntries.QuestionProgress;

import static com.paradigm.paradigm.MainActivity.course;

public class FIBQuestionFragment extends Fragment {

    public static FIBQuestionFragment newInstance() {
        return new FIBQuestionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.f_i_b_question_fragment, container, false);

        updateQuestionProgress(root);

        UserProgress userProgress = MainActivity.getUserProfile().getUserProgress();
        TextView questionTitle = root.findViewById(R.id.fibQuestionTitle);
        questionTitle.setText(userProgress.getCurrentQuestion().getQuestionName());
        TextView questionText = root.findViewById(R.id.fibQuestionContent);
        questionText.setText(userProgress.getCurrentQuestion().getQuestionText());

        CardView cardView = root.findViewById(R.id.checkFIBAnswer);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question question = userProgress.getCurrentQuestion();
                EditText answer = root.findViewById(R.id.fibQuestionEntry);
                String parsedAnswer;
                if (answer == null) {
                    parsedAnswer = "defaultAnswer";
                } else {
                    parsedAnswer = answer.getText().toString();
                }
                if (!question.checkAnswer(parsedAnswer, question.getAnswer(),
                        MainActivity.getUserProfile().getUserProgress())) {
                    Toast.makeText(requireContext(), "Try another answer.", Toast.LENGTH_SHORT).show();
                }
                updateQuestionProgress(root);
            }
        });
        return root;
    }

    private void updateQuestionProgress(View root) {
        UserProgress userProgress = MainActivity.getUserProfile().getUserProgress();
        QuestionProgress questionProgress = userProgress.getQuestionProgress(userProgress.getCurrentQuestion());
        if (questionProgress.isComplete()) {
            TextView text = root.findViewById(R.id.FIBQIndicatorText);
            String answerKey = "You have correctly answered this question before.\n" + "Correct answer: " + userProgress.getCurrentQuestion().getAnswer().getAnswer();
            text.setText(answerKey);
            text.setTextColor(getResources().getColor(R.color.white));
            CardView indicator = root.findViewById(R.id.FIBQProgressIndicator);
            indicator.setCardBackgroundColor(getResources().getColor(R.color.cardGreen));
        }
        MainActivity.getUserProfile().getUserProgress().isLessonComplete(userProgress.getCurrentLesson());
        CourseProgress courseProgress = userProgress.findCourseProgress(course.getName());
        String moduleName = course.getName() + "," + userProgress.getCurrentModule().getName();
        ModuleProgress moduleProgress = courseProgress.getModuleProgress(moduleName);
        moduleProgress.checkComplete();
        ((MainActivity) requireActivity()).saveProgress();
    }
}
