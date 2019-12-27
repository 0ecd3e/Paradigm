package com.paradigm.paradigm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paradigm.paradigm.exercises.answer.Answer;
import com.paradigm.paradigm.exercises.answer.MultipleChoiceAnswer;
import com.paradigm.paradigm.exercises.question.MultipleChoiceQuestion;
import com.paradigm.paradigm.exercises.question.Question;
import com.paradigm.paradigm.text.ContentModule;
import com.paradigm.paradigm.text.Course;
import com.paradigm.paradigm.text.Lesson;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ContentTest {
    @Test
    public void testSave() {
        Course java = new Course("java");
        ContentModule mod1 = new ContentModule("module1");
        ContentModule mod2 = new ContentModule("module2");
        ContentModule mod3 = new ContentModule("module3");

        Lesson l1 = new Lesson("lesson1");
        Lesson l2 = new Lesson("lesson2");
        Lesson l3 = new Lesson("lesson3");
        Lesson l4 = new Lesson("lesson4");
        Lesson l5 = new Lesson("lesson5");
        Lesson l6 = new Lesson("lesson6");
        Lesson l7 = new Lesson("lesson7");
        Lesson l8 = new Lesson("lesson8");
        Lesson l9 = new Lesson("lesson9");

        mod1.addLesson(l1);
        mod1.addLesson(l2);
        mod1.addLesson(l3);
        mod2.addLesson(l4);
        mod2.addLesson(l5);
        mod2.addLesson(l6);
        mod3.addLesson(l7);
        mod3.addLesson(l8);
        mod3.addLesson(l9);

        Answer a1 = new MultipleChoiceAnswer("A");
        Answer a2 = new MultipleChoiceAnswer("B");
        Answer a3 = new MultipleChoiceAnswer("C");
        Answer a4 = new MultipleChoiceAnswer("D");
        Answer a5 = new MultipleChoiceAnswer("E");
        Answer a6 = new MultipleChoiceAnswer("F");

        Question q1 = new MultipleChoiceQuestion("q1", a1);
        Question q2 = new MultipleChoiceQuestion("q2", a2);
        Question q3 = new MultipleChoiceQuestion("q3", a3);
        Question q4 = new MultipleChoiceQuestion("q4", a4);
        Question q5 = new MultipleChoiceQuestion("q5", a5);
        Question q6 = new MultipleChoiceQuestion("q6", a6);

        mod1.addQuestion(q1);
        mod1.addQuestion(q2);
        mod2.addQuestion(q3);
        mod2.addQuestion(q4);
        mod3.addQuestion(q5);
        mod3.addQuestion(q6);

        java.addModule(mod1);
        java.addModule(mod2);
        java.addModule(mod3);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/test.json"), java);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoad() {
        Course test = new Course("testing");

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            test = objectMapper.readValue(new File("courses/java/test.json"), Course.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
