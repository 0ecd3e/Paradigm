package com.paradigm.paradigm.exercises.question;

import com.paradigm.paradigm.exercises.answer.Answer;

public class MultipleChoiceQuestion extends Question {
    public MultipleChoiceQuestion() {
        super();
        questionType = "fillInBlankQuestion";
    }

    public MultipleChoiceQuestion(String questionName, String questionText, Answer answer) {
        super(questionName, questionText, answer);
        questionType = "multipleChoiceQuestion";
    }
}

