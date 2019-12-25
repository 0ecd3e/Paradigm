package com.paradigm.paradigm.text;

import com.paradigm.paradigm.exercises.question.Question;

import java.util.HashSet;
import java.util.Set;

public class Module extends Content {
    private Set<Lesson> lessons;
    private Set<Question> questions;

    public Module(String name) {
        super(name);
        lessons = new HashSet<>();
        questions = new HashSet<>();
    }

    @Override
    public String getFileName() {
        return null;
    }
}
