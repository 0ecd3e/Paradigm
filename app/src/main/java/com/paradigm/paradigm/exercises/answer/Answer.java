package com.paradigm.paradigm.exercises.answer;

public abstract class Answer {
    protected String theAnswer;

    public Answer(String answer) {
        theAnswer = answer;
    }

    public String getAnswer() {
        return theAnswer;
    }

    public void setAnswer(String answer) {
        theAnswer = answer;
    }
}
