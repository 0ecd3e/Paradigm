package com.paradigm.paradigm.text.io;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.paradigm.paradigm.exercises.answer.Answer;
import com.paradigm.paradigm.exercises.answer.FillInBlankAnswer;
import com.paradigm.paradigm.exercises.answer.MultipleChoiceAnswer;
import com.paradigm.paradigm.exercises.question.FillInBlankQuestion;
import com.paradigm.paradigm.exercises.question.MultipleChoiceQuestion;
import com.paradigm.paradigm.exercises.question.Question;
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
        courseName = courseName.replace("\"", "");
        Course course = new Course(courseName);
//        System.out.println("!Deserializer " + course);

        Iterator<String> moduleNames = treeNode.get("modules").fieldNames();
        while (moduleNames.hasNext()) {
            String moduleName = moduleNames.next();
            ContentModule contentModule = new ContentModule(moduleName);

            Iterator<String> lessonNames = treeNode.get("modules").get(moduleName).get("lessons").fieldNames();
            while (lessonNames.hasNext()) {
                String lessonName = lessonNames.next();
                Lesson lesson = new Lesson(lessonName);
                lesson.setParentContentModule(moduleName);
                lesson.setParentCourse(courseName);
                contentModule.addLesson(lesson);
            }

            Iterator<String> questionNames = treeNode.get("modules").get(moduleName).get("questions").fieldNames();
            while (questionNames.hasNext()) {
                String questionName = questionNames.next();
                TreeNode currentQuestion = treeNode.get("modules").get(moduleName).get("questions").get(questionName);

                String questionText = currentQuestion.get("text").toString();
                questionText = questionText.replace("\"", "");

                String questionType = currentQuestion.get("type").toString();
                questionType = questionType.replace("\"", "");

                Question question = null;
                Answer answer = null;

                if (questionType.equals("multipleChoiceQuestion")) {
                    String mca = currentQuestion.get("answer").get("bestAnswer").toString();
                    answer = new MultipleChoiceAnswer(mca);
                    question = new MultipleChoiceQuestion(questionName, questionText, answer);
                } else if (questionType.equals("fillInBlankQuestion")) {
                    Iterator<String> acceptableAnswers = currentQuestion.get("answer").get("acceptedAnswers").fieldNames();
                    System.out.println("!!!");
                    while (acceptableAnswers.hasNext()) {
                        answer = new FillInBlankAnswer();
                        String next = acceptableAnswers.next();
                        System.out.println(next);
                        ((FillInBlankAnswer) answer).addAlternativeAnswer(next);
                    }
                    question = new FillInBlankQuestion(questionName, questionText, answer);
                }

                question.setParentContentModule(moduleName);
                question.setParentCourse(courseName);
                contentModule.addQuestion(question);
            }

            contentModule.setParentCourse(courseName);
            course.addModule(contentModule);
        }

        return course;
    }
}
