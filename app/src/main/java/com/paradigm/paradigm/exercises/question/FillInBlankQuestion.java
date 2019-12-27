package com.paradigm.paradigm.exercises.question;

import com.paradigm.paradigm.exercises.answer.Answer;
import com.paradigm.paradigm.exercises.answer.FillInBlankAnswer;
import com.paradigm.paradigm.profile.UserProgress;

import java.util.List;

public class FillInBlankQuestion extends Question {
    public FillInBlankQuestion() {
        super();
    }

    public FillInBlankQuestion(String questionName, String questionText, Answer answer) {
        super(questionName, questionText, answer);
    }

    @Override
    public void checkAnswer(String input, Answer answer, UserProgress userProgress) {
        List<String> answerList = ((FillInBlankAnswer) answer).getAcceptedAnswers();
        boolean answerMatched = false;
        for (String possibleAnswer : answerList) {
            if (input.equals(possibleAnswer)) {
//                setAnsweredCorrectly(true);
                userProgress.markQuestionCorrect(this);
                answerMatched = true;
            }
        }

        if (!answerMatched) {
//            setAnsweredCorrectly(false);
            userProgress.markQuestionIncorrect(this);
        }
    }
}
