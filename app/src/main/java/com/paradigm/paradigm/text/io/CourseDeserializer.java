package com.paradigm.paradigm.text.io;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.paradigm.paradigm.exercises.question.Question;
import com.paradigm.paradigm.text.ContentModule;
import com.paradigm.paradigm.text.Course;
import com.paradigm.paradigm.text.Lesson;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class CourseDeserializer extends StdDeserializer<Course> {
    public CourseDeserializer() {
        this(Course.class);
    }

    protected CourseDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Course deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        TreeNode treeNode = p.readValueAsTree();
        String courseName;
        Map<String, ContentModule> modules;
        Map<String, Lesson> lessons;
        Map<String, Question> questions;

        Iterator<String> fieldNames = treeNode.fieldNames();
        while (fieldNames.hasNext()) {
            String field = fieldNames.next();
        }

        return null;
    }
}
