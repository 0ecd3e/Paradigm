package com.paradigm.paradigm.exercises.question;

import com.paradigm.paradigm.exercises.answer.Answer;
import com.paradigm.paradigm.exercises.answer.FillInBlankAnswer;

import java.util.List;

public class FillInBlankQuestion extends Question {
    public FillInBlankQuestion() {
        super();
    }

    public FillInBlankQuestion(String question, Answer answer) {
        super(question, answer);
    }

    @Override
    public void checkAnswer(String input, Answer answer) {
        List<String> answerList = ((FillInBlankAnswer) answer).getAnswerList();
        boolean answerMatched = false;
        for (String possibleAnswer : answerList) {
            if (input.equals(possibleAnswer)) {
                setAnsweredCorrectly(true);
                answerMatched = true;
            }
        }

        if (!answerMatched) {
            setAnsweredCorrectly(false);
        }
    }
}
