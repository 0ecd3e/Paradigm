package com.paradigm.paradigm.exercises.question;

import com.paradigm.paradigm.exercises.answer.Answer;
import com.paradigm.paradigm.exercises.answer.FillInBlankAnswer;
import com.paradigm.paradigm.profile.UserProgress;

import java.util.List;

public class FillInBlankQuestion extends Question {
    public FillInBlankQuestion() {
        super();
        questionType = "fillInBlankQuestion";
    }

    public FillInBlankQuestion(String questionName, String questionText, Answer answer) {
        super(questionName, questionText, answer);
        questionType = "fillInBlankQuestion";
    }

    @Override
    public boolean checkAnswer(String input, Answer answer, UserProgress userProgress) {
        List<String> answerList = ((FillInBlankAnswer) answer).getAcceptedAnswers();
        boolean answerMatched = false;
        for (String possibleAnswer : answerList) {
            if (input.equals(possibleAnswer)) {
                userProgress.markQuestionCorrect(this);
                answerMatched = true;
            }
        }

        if (!answerMatched) {
            userProgress.markQuestionIncorrect(this);
        }
        return answerMatched;
    }
}
