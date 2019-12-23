package com.paradigm.paradigm;

import com.paradigm.paradigm.exercises.answer.MultipleChoiceAnswer;
import com.paradigm.paradigm.exercises.question.MultipleChoiceQuestion;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MCQTest {
    @Test
    public void testQuestion() {
        MultipleChoiceAnswer answer = new MultipleChoiceAnswer("B");
        String question = "What is the second letter of the alphabet?";
        MultipleChoiceQuestion mcq = new MultipleChoiceQuestion(question, answer);

        assertFalse(mcq.isAnsweredCorrectly());
        mcq.checkAnswer("Z", answer);
        assertFalse(mcq.isAnsweredCorrectly());
        mcq.checkAnswer("B", answer);
        assertTrue(mcq.isAnsweredCorrectly());
    }
}
