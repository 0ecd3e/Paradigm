package com.paradigm.paradigm;

import com.paradigm.paradigm.exercises.answer.MultipleChoiceAnswer;
import com.paradigm.paradigm.exercises.question.MultipleChoiceQuestion;
import com.paradigm.paradigm.profile.UserProgress;
import com.paradigm.paradigm.text.ContentModule;
import com.paradigm.paradigm.text.Course;
import com.paradigm.paradigm.text.Lesson;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MCQTest {
    @Test
    public void testQuestion() {
        MultipleChoiceAnswer answer = new MultipleChoiceAnswer("B");
        String questionText = "What is the second letter of the alphabet?";
        MultipleChoiceQuestion mcq = new MultipleChoiceQuestion("q1", questionText, answer);

        ContentModule module = new ContentModule("module1");
        Lesson lesson = new Lesson("lesson2");
        lesson.addQuestion(mcq);
        module.addLesson(lesson);
        Course course = new Course("java");
        course.addModule(module);
        course.setParents();

        UserProgress userProgress = new UserProgress();
        userProgress.addCourse(course);

        assertFalse(userProgress.isAnsweredCorrectly(mcq));
        mcq.checkAnswer("Z", answer, userProgress);
        assertFalse(userProgress.isAnsweredCorrectly(mcq));
        mcq.checkAnswer("B", answer, userProgress);
        assertTrue(userProgress.isAnsweredCorrectly(mcq));
    }
}
