package com.paradigm.paradigm.exercises.answer;

import java.util.ArrayList;
import java.util.List;

public class FillInBlankAnswer extends Answer {
    private List<String> acceptedAnswers;

    public FillInBlankAnswer() {
        super();
        acceptedAnswers = new ArrayList<>();
        answerType = "fillInBlankAnswer";
    }

    public FillInBlankAnswer(String answer) {
        super(answer);
        acceptedAnswers = new ArrayList<>();
        addAlternativeAnswer(answer);
        answerType = "fillInBlankAnswer";
    }

    public void addAlternativeAnswer(String answer) {
        if (!containsAnswer(answer)) {
            acceptedAnswers.add(answer);
        }
    }

    public void removeAlternativeAnswer(String answer) {
        acceptedAnswers.remove(answer);
    }

    public List<String> getAcceptedAnswers() {
        return acceptedAnswers;
    }

    @Override
    public String toString() {
        System.out.println("ANSWERS_START");
        for (String answer : acceptedAnswers) {
            System.out.println(answer);
        }
        System.out.println("ANSWERS_END");
        return "FIBA END";
    }

    private boolean containsAnswer(String answer) {
        for (String acceptedAnswer : acceptedAnswers) {
            if (acceptedAnswer.equals(answer))
                return true;
        }
        return false;
    }
}
