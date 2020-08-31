package com.paradigm.paradigm;

import com.paradigm.paradigm.exercises.answer.FillInBlankAnswer;
import com.paradigm.paradigm.exercises.question.FillInBlankQuestion;
import com.paradigm.paradigm.profile.UserProgress;
import com.paradigm.paradigm.text.ContentModule;
import com.paradigm.paradigm.text.Course;
import com.paradigm.paradigm.text.Lesson;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FIBTest {
    @Test
    public void testQuestion() {
        FillInBlankAnswer answer = new FillInBlankAnswer("public");
        answer.addAlternativeAnswer("protected");
        answer.addAlternativeAnswer("private");
        String questionText = "What are the visibility keywords?";
        FillInBlankQuestion fibq = new FillInBlankQuestion("q1", questionText, answer);

        ContentModule module = new ContentModule("module1");
        Lesson lesson = new Lesson("lesson1");
        lesson.addQuestion(fibq);
        module.addLesson(lesson);
        Course course = new Course("java");
        course.addModule(module);
        course.setParents();

        UserProgress userProgress = new UserProgress();
        userProgress.addCourse(course);

        assertFalse(userProgress.isAnsweredCorrectly(fibq));
        fibq.checkAnswer("Z", answer, userProgress);
        assertFalse(userProgress.isAnsweredCorrectly(fibq));
        fibq.checkAnswer("private", answer, userProgress);
        assertTrue(userProgress.isAnsweredCorrectly(fibq));
        fibq.checkAnswer("Z", answer, userProgress);
        assertFalse(userProgress.isAnsweredCorrectly(fibq));
        fibq.checkAnswer("protected", answer, userProgress);
        assertTrue(userProgress.isAnsweredCorrectly(fibq));
        fibq.checkAnswer("public", answer, userProgress);
        assertTrue(userProgress.isAnsweredCorrectly(fibq));
    }
}
