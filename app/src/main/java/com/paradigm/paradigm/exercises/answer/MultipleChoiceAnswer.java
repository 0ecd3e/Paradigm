package com.paradigm.paradigm.exercises.answer;

public class MultipleChoiceAnswer extends Answer {
    public MultipleChoiceAnswer() {
        super();
    }

    public MultipleChoiceAnswer(String answer) {
        super(answer);
    }

    @Override
    public String getAnswerType() {
        return "multipleChoiceAnswer";
    }
}
