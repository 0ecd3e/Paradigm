package com.paradigm.paradigm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paradigm.paradigm.profile.UserProgress;
import com.paradigm.paradigm.text.Course;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.fail;

public class userProgressTest {
    @Test
    public void testSaveProgress() {
        Course test = new Course("testing");

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            test = objectMapper.readValue(new File("src/courseJava.json"), Course.class);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }

        UserProgress up = new UserProgress();
        up.addCourse(test);
    }
}
