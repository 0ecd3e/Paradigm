package com.paradigm.paradigm.exercises.answer;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MultipleChoiceAnswer.class),
        @JsonSubTypes.Type(value = FillInBlankAnswer.class),
})
public abstract class Answer implements Serializable {
    protected String bestAnswer;

    public Answer() {
        super();
    }

    public Answer(String answer) {
        bestAnswer = answer;
    }

    public String getAnswer() {
        return bestAnswer;
    }

    public void setAnswer(String answer) {
        bestAnswer = answer;
    }

    public abstract String getAnswerType();
}
