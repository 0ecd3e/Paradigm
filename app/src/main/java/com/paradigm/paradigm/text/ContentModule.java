package com.paradigm.paradigm.text;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.paradigm.paradigm.exercises.question.Question;

import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class ContentModule extends Content {

    @JsonProperty("lessons")
    private List<Lesson> lessons;
    @JsonProperty("questions")
    private List<Question> questions;
    @JsonProperty("parentCourse")
    private String parentCourse;

    public ContentModule() {
        super();
    }

    public ContentModule(String name) {
        super(name);
        lessons = new ArrayList<>();
        questions = new ArrayList<>();
    }

    @Override
    public String descriptionPath() {
        return DIR_ROOT + parentCourse + "/" + name + "/" + DESC_FILE;
    }

    public String questionsPath() {
        return DIR_ROOT + parentCourse + "/" + name + "/" + Q_FILE;
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

    public List<Lesson> getLessons() {
        return lessons;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
        lesson.setParentContentModule(name);
        lesson.setParentCourse(parentCourse);
    }

    public void removeLesson(Lesson lesson) {
        lessons.remove(lesson);
        lesson.clearParentContentModule();
        lesson.clearParentCourse();
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

    public void setParents() {
        for (Lesson lesson : lessons) {
            lesson.setParentContentModule(name);
            lesson.setParentCourse(parentCourse);
        }

        for (Question question : questions) {
            question.setParentContentModule(name);
            question.setParentCourse(parentCourse);
        }
    }
}
