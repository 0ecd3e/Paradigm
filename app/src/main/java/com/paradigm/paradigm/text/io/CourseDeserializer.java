package com.paradigm.paradigm.text.io;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.paradigm.paradigm.text.ContentModule;
import com.paradigm.paradigm.text.Course;
import com.paradigm.paradigm.text.Lesson;

import java.io.IOException;
import java.util.Iterator;

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
        String courseName = treeNode.get("name").toString();
        Course course = new Course(courseName);

        Iterator<String> moduleNames = treeNode.get("modules").fieldNames();
        while (moduleNames.hasNext()) {
            String moduleName = moduleNames.next();
            String moduleParentCourse = courseName;
            ContentModule contentModule = new ContentModule(moduleName);

            Iterator<String> lessonNames = treeNode.get("lessons").fieldNames();
            while (lessonNames.hasNext()) {
                String lessonName = lessonNames.next();
                String parentContentModule = moduleName;
                String lessonParentCourse = courseName;
                Lesson lesson = new Lesson(lessonName);
                lesson.setParentContentModule(parentContentModule);
                lesson.setParentCourse(lessonParentCourse);
                contentModule.addLesson(lesson);
            }

            Iterator<String> questionNames = treeNode.get("questions").fieldNames();
            while (questionNames.hasNext()) {
                String questionName = questionNames.next();
                String parentContentModule = moduleName;
                String questionParentCourse = courseName;
//                Question question = new Question(questionName);
//                question.setParentContentModule(parentContentModule);

            }
        }

        return null;
    }
}
