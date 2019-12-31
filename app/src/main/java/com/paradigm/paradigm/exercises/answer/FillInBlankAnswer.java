package com.paradigm.paradigm.exercises.answer;

import java.util.ArrayList;
import java.util.List;

public class FillInBlankAnswer extends Answer {
    private List<String> acceptedAnswers;

    public FillInBlankAnswer() {
        super();
    }

    public FillInBlankAnswer(String answer) {
        super(answer);
        acceptedAnswers = new ArrayList<>();
        acceptedAnswers.add(answer);
    }

    @Override
    public String getAnswerType() {
        return "fillInBlankAnswer";
    }

    public void addAlternativeAnswer(String answer) {
        acceptedAnswers.add(answer);
    }

    public void removeAlternativeAnswer(String answer) {
        acceptedAnswers.remove(answer);
    }

    public List<String> getAcceptedAnswers() {
        return acceptedAnswers;
    }
}
