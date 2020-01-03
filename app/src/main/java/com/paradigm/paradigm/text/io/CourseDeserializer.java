package com.paradigm.paradigm.text.io;

import com.fasterxml.jackson.core.JsonParser;
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
            throws IOException {

        TreeNode treeNode = p.readValueAsTree();
        String courseName = treeNode.get("name").toString();
        courseName = courseName.replace("\"", "");
        Course course = new Course(courseName);

        Iterator<String> moduleNames = treeNode.get("modules").fieldNames();
        while (moduleNames.hasNext()) {
            String moduleName = moduleNames.next();
            ContentModule contentModule = new ContentModule(moduleName);

            Iterator<String> lessonNames = treeNode.get("modules").get(moduleName).get("lessons").fieldNames();
            while (lessonNames.hasNext()) {
                String lessonName = lessonNames.next();
                Lesson lesson = new Lesson(lessonName);
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

                String bestAnswer = currentQuestion.get("answer").get("bestAnswer").toString();
                bestAnswer = bestAnswer.replace("\"", "");
                if (questionType.equals("multipleChoiceQuestion")) {
                    answer = new MultipleChoiceAnswer(bestAnswer);
                    question = new MultipleChoiceQuestion(questionName, questionText, answer);
                } else if (questionType.equals("fillInBlankQuestion")) {
                    System.out.println("FIBQ_DESERIALIZER");
                    answer = new FillInBlankAnswer(bestAnswer);
                    TreeNode acceptableAnswersArray = currentQuestion.get("answer").get("acceptedAnswers");

                    int index = 0;
                    while (index < acceptableAnswersArray.size()) {
                        String next = acceptableAnswersArray.get(index).get("acceptedAnswer" + (index + 1)).toString();
                        next = next.replace("\"", "");
                        System.out.println(next);
                        ((FillInBlankAnswer) answer).addAlternativeAnswer(next);
                        index++;
                    }

                    System.out.println("END OF WHILE LOOP DESERIALIZER");
                    System.out.println(answer);
                    question = new FillInBlankQuestion(questionName, questionText, answer);
                }

                contentModule.addQuestion(question);
            }

            course.addModule(contentModule);
        }

        course.setParents();
        return course;
    }
}
