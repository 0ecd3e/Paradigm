package com.paradigm.paradigm.text;

import com.paradigm.paradigm.exercises.question.Question;

import java.util.HashSet;
import java.util.Set;

public class ContentModule extends Content {
    private Set<Lesson> lessons;
    private Set<Question> questions;
    private String parentCourse;

    public ContentModule() {
        super();
    }

    public ContentModule(String name) {
        super(name);
        lessons = new HashSet<>();
        questions = new HashSet<>();
    }

    @Override
    public String descriptionPath() {
        return DIR_ROOT + parentCourse + "/" + name + "/" + DESC_FILE;
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

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public Set<Question> getQuestions() {
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
