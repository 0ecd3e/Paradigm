package com.paradigm.paradigm.exercises.answer;

import java.util.ArrayList;
import java.util.List;

public class FillInBlankAnswer extends Answer {
    private List<String> answerList;

    public FillInBlankAnswer() {
        super();
    }

    public FillInBlankAnswer(String answer) {
        super(answer);
        answerList = new ArrayList<>();
        answerList.add(answer);
    }

    public void addAlternativeAnswer(String answer) {
        answerList.add(answer);
    }

    public void removeAlternativeAnswer(String answer) {
        answerList.remove(answer);
    }

    public List<String> getAnswerList() {
        return answerList;
    }
}
