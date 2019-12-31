package com.paradigm.paradigm.exercises.question;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.paradigm.paradigm.exercises.answer.Answer;
import com.paradigm.paradigm.profile.UserProgress;

import java.io.Serializable;


@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MultipleChoiceQuestion.class),
        @JsonSubTypes.Type(value = FillInBlankQuestion.class),
})
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public abstract class Question implements Serializable {
    protected String questionText;
    protected String questionName;
    protected Answer answer;
    //    protected boolean answeredCorrectly;
    protected String parentContentModule;
    protected String parentCourse;

    public Question() {
        super();
    }

    public Question(String questionName, String questionText, Answer answer) {
        this.questionName = questionName;
        this.questionText = questionText;
        this.answer = answer;
//        answeredCorrectly = false;
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

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String name) {
        this.questionName = name;
    }

    public abstract String getQuestionType();

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public void checkAnswer(String input, Answer answer, UserProgress userProgress) {
        if (input.equals(answer.getAnswer())) {
            userProgress.markQuestionCorrect(this);
        } else {
            userProgress.markQuestionIncorrect(this);
        }
    }
}
