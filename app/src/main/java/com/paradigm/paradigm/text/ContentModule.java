package com.paradigm.paradigm.text;

import com.paradigm.paradigm.exercises.question.Question;

import java.util.HashSet;
import java.util.Set;

public class ContentModule extends Content {
    private Set<Lesson> lessons;
    private Set<Question> questions;

    public ContentModule() {
        super();
    }

    public ContentModule(String name) {
        super(name);
        lessons = new HashSet<>();
        questions = new HashSet<>();
    }

    @Override
    public String storedName() {
        return null;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public void removeLesson(Lesson lesson) {
        lessons.remove(lesson);
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
    }
}
