package com.paradigm.paradigm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paradigm.paradigm.exercises.answer.Answer;
import com.paradigm.paradigm.exercises.answer.FillInBlankAnswer;
import com.paradigm.paradigm.exercises.answer.MultipleChoiceAnswer;
import com.paradigm.paradigm.exercises.question.FillInBlankQuestion;
import com.paradigm.paradigm.exercises.question.MultipleChoiceQuestion;
import com.paradigm.paradigm.exercises.question.Question;
import com.paradigm.paradigm.text.ContentModule;
import com.paradigm.paradigm.text.Course;
import com.paradigm.paradigm.text.Lesson;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.fail;

public class ContentTest {
    @Test
    public void courseCreator() {
        Course courseJava = new Course("Java Basics");
        courseJava.setDescription("defaultDescription");

        ContentModule m1 = new ContentModule("Module 1: Introduction");
        ContentModule m2 = new ContentModule("Module 2: OOP Basics");
        ContentModule m3 = new ContentModule("Module 3: Basic Syntax");
        ContentModule m4 = new ContentModule("Module 4: Data Types and Structures");
        ContentModule m5 = new ContentModule("Module 5: Decision Making");
        ContentModule m6 = new ContentModule("Module 6: Loops");
        ContentModule m7 = new ContentModule("Module 7: Exceptions");

        Lesson m1l1 = new Lesson("Lesson 1: What is Java?");

        Lesson m2l1 = new Lesson("Lesson 1: Classes and Interfaces");
        Lesson m2l2 = new Lesson("Lesson 2: Methods");

        Lesson m3l1 = new Lesson("Lesson 1: Elements of Code");
        Lesson m3l2 = new Lesson("Lesson 2: Code Organization");
        Lesson m3l3 = new Lesson("Lesson 3: Style Guides");

        Lesson m4l1 = new Lesson("Lesson 1: Access Modifiers");
        Lesson m4l2 = new Lesson("Lesson 2: Data Types");
        Lesson m4l3 = new Lesson("Lesson 3: Arrays");

        Lesson m5l1 = new Lesson("Lesson 1: If-statements");
        Lesson m5l2 = new Lesson("Lesson 2: Switch-case");

        Lesson m6l1 = new Lesson("Lesson 1: For and For-each Loops");
        Lesson m6l2 = new Lesson("Lesson 2: While Loops");

        Lesson m7l1 = new Lesson("Lesson 1: Exceptions");

        //m1, l1
        Answer m1l1q1a = new MultipleChoiceAnswer("A");
        FillInBlankAnswer m1l1q2a = new FillInBlankAnswer("B");
        Answer m1l1q3a = new MultipleChoiceAnswer("C");

        m1l1q2a.addAlternativeAnswer("1");
        m1l1q2a.addAlternativeAnswer("2");
        m1l1q2a.addAlternativeAnswer("3");

        Question m1l1q1 = new MultipleChoiceQuestion("Q1", "defaultQuestionText", m1l1q1a);
        Question m1l1q2 = new FillInBlankQuestion("Q2", "defaultQuestionText2", m1l1q2a);
        Question m1l1q3 = new MultipleChoiceQuestion("Q3", "defaultQuestionText3", m1l1q3a);

        m1l1.addQuestion(m1l1q1);
        m1l1.addQuestion(m1l1q2);
        m1l1.addQuestion(m1l1q3);

        //m2, l1
        Answer m2l1q1a = new MultipleChoiceAnswer("A");
        FillInBlankAnswer m2l1q2a = new FillInBlankAnswer("B");
        Answer m2l1q3a = new MultipleChoiceAnswer("C");

        m2l1q2a.addAlternativeAnswer("1");
        m2l1q2a.addAlternativeAnswer("2");
        m2l1q2a.addAlternativeAnswer("3");

        Question m2l1q1 = new MultipleChoiceQuestion("Q1", "defaultQuestionText", m2l1q1a);
        Question m2l1q2 = new FillInBlankQuestion("Q2", "defaultQuestionText2", m2l1q2a);
        Question m2l1q3 = new MultipleChoiceQuestion("Q3", "defaultQuestionText3", m2l1q3a);

        m2l1.addQuestion(m2l1q1);
        m2l1.addQuestion(m2l1q2);
        m2l1.addQuestion(m2l1q3);

        // adding lessons
        m1.addLesson(m1l1);

        m2.addLesson(m2l1);
        m2.addLesson(m2l2);

        m3.addLesson(m3l1);
        m3.addLesson(m3l2);
        m3.addLesson(m3l3);

        m4.addLesson(m4l1);
        m4.addLesson(m4l2);
        m4.addLesson(m4l3);

        m5.addLesson(m5l1);
        m5.addLesson(m5l2);

        m6.addLesson(m6l1);
        m6.addLesson(m6l2);

        m7.addLesson(m7l1);

        courseJava.addModule(m1);
        courseJava.addModule(m2);
        courseJava.addModule(m3);
        courseJava.addModule(m4);
        courseJava.addModule(m5);
        courseJava.addModule(m6);
        courseJava.addModule(m7);
        courseJava.setParents();

        // save to json file
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/courseJava.json"), courseJava);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    public void createQuestionJson() {
        ContentModule testMod = new ContentModule("module2");

        Lesson lesson = new Lesson("lesson5");

        Answer a1 = new MultipleChoiceAnswer("Z");
        FillInBlankAnswer a2 = new FillInBlankAnswer("X");
        Answer a3 = new MultipleChoiceAnswer("Z");
        FillInBlankAnswer a4 = new FillInBlankAnswer("X");
        Answer a5 = new MultipleChoiceAnswer("Z");
        FillInBlankAnswer a6 = new FillInBlankAnswer("X");

        a2.addAlternativeAnswer("pubadfdaflic");
        a2.addAlternativeAnswer("privafdvdfvate");
        a2.addAlternativeAnswer("vovadfvsdfid");

        a4.addAlternativeAnswer("byvdfs xfte");
        a4.addAlternativeAnswer("stvafddfring");
        a4.addAlternativeAnswer("aradfvray");

        a6.addAlternativeAnswer("fvfsoo");
        a6.addAlternativeAnswer("bsfdar");

        Question q1 = new MultipleChoiceQuestion("q1", "testq1", a1);
        Question q2 = new FillInBlankQuestion("q2", "testq2", a2);
        Question q3 = new MultipleChoiceQuestion("q3", "sdlkjjf;", a3);
        Question q4 = new FillInBlankQuestion("q4", "hfajlrhfn", a4);
        Question q5 = new MultipleChoiceQuestion("q5", "f0y3w48uhrs", a5);
        Question q6 = new FillInBlankQuestion("q6", "uhfwhulgi45", a6);

        lesson.addQuestion(q1);
        lesson.addQuestion(q2);
        lesson.addQuestion(q3);
        lesson.addQuestion(q4);
        lesson.addQuestion(q5);
        lesson.addQuestion(q6);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/questions.json"), testMod);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     */

    @Test
    public void testLoad() {
        Course test = new Course("testing");

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            test = objectMapper.readValue(new File("src/courseJava.json"), Course.class);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

}
