package com.paradigm.paradigm.text;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.paradigm.paradigm.exercises.question.Question;

import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Lesson extends Content {
    @JsonProperty("lessonContent")
    private String lessonContent;
    @JsonProperty("parentContentModule")
    private String parentContentModule;
    @JsonProperty("parentCourse")
    private String parentCourse;
    @JsonProperty("questions")
    private List<Question> questions;


    public Lesson() {
        super();
    }

    public Lesson(String name) {
        super(name);
        questions = new ArrayList<>();
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

    @Override
    public String descriptionPath() {
        String descModuleName = parentContentModule.substring(0, 8);
        String descLessonName = getName().substring(0, 8);
        return DIR_ROOT + parentCourse + "/" + descModuleName + "/" + descLessonName + "/" + DESC_FILE;
    }

    public String questionsPath() {
        String descModuleName = parentContentModule.substring(0, 8);
        String descLessonName = getName().substring(0, 8);
        return DIR_ROOT + parentCourse + "/" + descModuleName + "/" + descLessonName + "/" + Q_FILE;
    }

    public String getLessonContent() {
        return lessonContent;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
    }

    public void replaceQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setLessonContent(String content) {
        this.lessonContent = content;
    }

    public String lessonContentPath() {
        String descModuleName = parentContentModule.substring(0, 8);
        String descLessonName = getName().substring(0, 8);
        return DIR_ROOT + parentCourse + "/" + descModuleName + "/" + descLessonName + "/" + descLessonName + ".txt";
    }

    public void setParents() {
        for (Question question : questions) {
            question.setParentLesson(name);
            question.setParentContentModule(getParentContentModule());
            question.setParentCourse(getParentCourse());
        }
    }

    @NonNull
    @Override
    public String toString() {
        System.out.println("N " + name);
        System.out.println("PCM " + parentContentModule);
        System.out.println("PC " + parentCourse);
        return "LESSON";
    }
}
