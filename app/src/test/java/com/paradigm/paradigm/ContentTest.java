package com.paradigm.paradigm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paradigm.paradigm.text.Course;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ContentTest {
    @Test
    public void testLoadFromJSON() throws FileNotFoundException {
        Course test = new Course("testName");

        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = new FileInputStream("/src/main/assets/course1.json");
        try {
            test = objectMapper.readValue(inputStream, Course.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
