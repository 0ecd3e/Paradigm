package com.paradigm.paradigm.exercises.question;

import com.paradigm.paradigm.exercises.answer.Answer;

public abstract class Question {
    protected String question;
    protected Answer answer;
    protected boolean answeredCorrectly;

    protected Question(String question, Answer answer) {
        this.question = question;
        this.answer = answer;
        answeredCorrectly = false;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public boolean isAnsweredCorrectly() {
        return answeredCorrectly;
    }

    public void setAnsweredCorrectly(boolean status) {
        answeredCorrectly = status;
    }

    public void checkAnswer(String input, Answer answer) {
        if (input.equals(answer.getAnswer())) {
            setAnsweredCorrectly(true);
        } else {
            setAnsweredCorrectly(false);
        }
    }
}
