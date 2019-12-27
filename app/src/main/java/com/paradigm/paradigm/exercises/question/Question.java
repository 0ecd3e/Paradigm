package com.paradigm.paradigm.exercises.question;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.paradigm.paradigm.exercises.answer.Answer;

import java.io.Serializable;


@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MultipleChoiceQuestion.class),
        @JsonSubTypes.Type(value = FillInBlankQuestion.class),
})
public abstract class Question implements Serializable {
    protected String question;
    protected Answer answer;
    protected boolean answeredCorrectly;
    protected String parentContentModule;
    protected String parentCourse;

    public Question() {
        super();
    }

    public Question(String question, Answer answer) {
        this.question = question;
        this.answer = answer;
        answeredCorrectly = false;
    }

    public String getParentContentModule() {
        return parentContentModule;
    }

    public void setParentContentModule(String contentModule) {
        this.parentContentModule = contentModule;
    }

    public void clearParentContentModule() {
        this.parentContentModule = null;
    }

    public void clearParentCourse() {
        parentCourse = null;
    }

    public String getParentCourse() {
        return parentCourse;
    }

    public void setParentCourse(String course) {
        this.parentCourse = course;
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
