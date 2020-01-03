package com.paradigm.paradigm.text;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.paradigm.paradigm.exercises.question.Question;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class ContentModule extends Content {

    @JsonProperty("lessons")
    private Map<String, Lesson> lessons;
    @JsonProperty("questions")
    private Map<String, Question> questions;
    @JsonProperty("parentCourse")
    private String parentCourse;

    public ContentModule() {
        super();
    }

    public ContentModule(String name) {
        super(name);
        lessons = new HashMap<>();
        questions = new HashMap<>();
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

    public Map<String, Lesson> getLessons() {
        return lessons;
    }

    public Collection<Lesson> provideLessonValues() {
        return lessons.values();
    }

    public Collection<Question> provideQuestionValues() {
        return questions.values();
    }

    public Map<String, Question> getQuestions() {
        return questions;
    }

    public void addLesson(Lesson lesson) {
        lessons.put(lesson.getName(), lesson);
        lesson.setParentContentModule(name);
        lesson.setParentCourse(parentCourse);
    }

    public void removeLesson(Lesson lesson) {
        lessons.remove(lesson.getName());
        lesson.clearParentContentModule();
        lesson.clearParentCourse();
    }

    public void addQuestion(Question question) {
        questions.put(question.getQuestionName(), question);
    }

    public void removeQuestion(Question question) {
        questions.remove(question.getQuestionName());
    }

    public void replaceQuestions(Map<String, Question> questions) {
        this.questions = questions;
    }

    public void setParents() {
        for (Lesson lesson : lessons.values()) {
            lesson.setParentContentModule(name);
            lesson.setParentCourse(parentCourse);
        }

        for (Question question : questions.values()) {
            question.setParentContentModule(name);
            question.setParentCourse(parentCourse);
        }
    }
}
