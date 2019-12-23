package com.paradigm.paradigm.exercises.question;

import com.paradigm.paradigm.exercises.answer.Answer;
import com.paradigm.paradigm.exercises.answer.FillInBlankAnswer;

import java.util.List;

public class FillInBlankQuestion extends Question {
    protected FillInBlankQuestion(String question, Answer answer) {
        super(question, answer);
    }

    @Override
    public void checkAnswer(String input, Answer answer) {
        List<String> answerList = ((FillInBlankAnswer) answer).getAnswerList();
        for (String possibleAnswer : answerList) {
            if (input.equals(possibleAnswer)) {
                setAnsweredCorrectly(true);
            } else {
                setAnsweredCorrectly(false);
            }
        }
    }
}
