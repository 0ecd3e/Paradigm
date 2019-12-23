package com.paradigm.paradigm;

import com.paradigm.paradigm.exercises.answer.FillInBlankAnswer;
import com.paradigm.paradigm.exercises.question.FillInBlankQuestion;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FIBTest {
    @Test
    public void testQuestion() {
        FillInBlankAnswer answer = new FillInBlankAnswer("public");
        answer.addAlternativeAnswer("protected");
        answer.addAlternativeAnswer("private");
        String question = "What are the visibility keywords?";
        FillInBlankQuestion fibq = new FillInBlankQuestion(question, answer);

        assertFalse(fibq.isAnsweredCorrectly());
        fibq.checkAnswer("Z", answer);
        assertFalse(fibq.isAnsweredCorrectly());
        fibq.checkAnswer("private", answer);
        assertTrue(fibq.isAnsweredCorrectly());
        fibq.checkAnswer("Z", answer);
        assertFalse(fibq.isAnsweredCorrectly());
        fibq.checkAnswer("protected", answer);
        assertTrue(fibq.isAnsweredCorrectly());
        fibq.checkAnswer("public", answer);
        assertTrue(fibq.isAnsweredCorrectly());
    }
}
