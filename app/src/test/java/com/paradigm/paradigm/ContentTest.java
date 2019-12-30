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
        Lesson l4 = new Lesson("lesson1");
        Lesson l5 = new Lesson("lesson2");
        Lesson l6 = new Lesson("lesson3");
        Lesson l7 = new Lesson("lesson1");
        Lesson l8 = new Lesson("lesson2");
        Lesson l9 = new Lesson("lesson3");

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
        FillInBlankAnswer a2 = new FillInBlankAnswer("B");
        Answer a3 = new MultipleChoiceAnswer("C");
        FillInBlankAnswer a4 = new FillInBlankAnswer("D");
        Answer a5 = new MultipleChoiceAnswer("E");
        FillInBlankAnswer a6 = new FillInBlankAnswer("F");

        a2.addAlternativeAnswer("public");
        a2.addAlternativeAnswer("private");
        a2.addAlternativeAnswer("void");

        a4.addAlternativeAnswer("byte");
        a4.addAlternativeAnswer("string");
        a4.addAlternativeAnswer("array");

        a6.addAlternativeAnswer("foo");
        a6.addAlternativeAnswer("bar");

        Question q1 = new MultipleChoiceQuestion("q1", "fhiwj34", a1);
        Question q2 = new FillInBlankQuestion("q2", "hfalsjht3", a2);
        Question q3 = new MultipleChoiceQuestion("q1", "hfiu34h", a3);
        Question q4 = new FillInBlankQuestion("q2", "fjlw3j4ht4", a4);
        Question q5 = new MultipleChoiceQuestion("q1", "fjaljt34", a5);
        Question q6 = new FillInBlankQuestion("q2", "flw34tnw4", a6);

        mod1.addQuestion(q1);
        mod1.addQuestion(q2);
        mod2.addQuestion(q3);
        mod2.addQuestion(q4);
        mod3.addQuestion(q5);
        mod3.addQuestion(q6);

        java.addModule(mod1);
        java.addModule(mod2);
        java.addModule(mod3);
        java.setParents();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/courseJava.json"), java);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createQuestionJson() {
        ContentModule testMod = new ContentModule("module2");

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
        Question q3 = new MultipleChoiceQuestion("q1", "sdlkjjf;", a3);
        Question q4 = new FillInBlankQuestion("q2", "hfajlrhfn", a4);
        Question q5 = new MultipleChoiceQuestion("q1", "f0y3w48uhrs", a5);
        Question q6 = new FillInBlankQuestion("q2", "uhfwhulgi45", a6);

        testMod.addQuestion(q1);
        testMod.addQuestion(q2);
        testMod.addQuestion(q3);
        testMod.addQuestion(q4);
        testMod.addQuestion(q5);
        testMod.addQuestion(q6);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/questions.json"), testMod);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoad() {
        Course test = new Course("testing");

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            test = objectMapper.readValue(new File("src/courseJava.json"), Course.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
