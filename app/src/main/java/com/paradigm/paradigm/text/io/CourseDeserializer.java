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

@SuppressWarnings("SameParameterValue")
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
                handleQuestions(treeNode, moduleName, lesson, lessonName);
                contentModule.addLesson(lesson);
            }

            course.addModule(contentModule);
        }

        course.setParents();
        return course;
    }

    private void handleQuestions(TreeNode treeNode, String moduleName, Lesson lesson, String lessonName) {
        Iterator<String> questionNames = treeNode.get("modules").get(moduleName).get("lessons").get(lessonName).get("questions").fieldNames();
        while (questionNames.hasNext()) {
            String questionName = questionNames.next();
            TreeNode currentQuestion = treeNode.get("modules").get(moduleName).get("lessons").get(lessonName).get("questions").get(questionName);

            String questionText = currentQuestion.get("text").toString();
            questionText = questionText.replace("\"", "");
            questionText = questionText.replace("\\n", "\n");

            String questionType = currentQuestion.get("type").toString();
            questionType = questionType.replace("\"", "");

            Question question = null;
            Answer answer;

            String bestAnswer = currentQuestion.get("answer").get("bestAnswer").toString();
            bestAnswer = bestAnswer.replace("\"", "");
            if (questionType.equals("multipleChoiceQuestion")) {
                answer = new MultipleChoiceAnswer(bestAnswer);
                question = new MultipleChoiceQuestion(questionName, questionText, answer);
            } else if (questionType.equals("fillInBlankQuestion")) {
                answer = new FillInBlankAnswer(bestAnswer);
                TreeNode acceptableAnswersArray = currentQuestion.get("answer").get("acceptedAnswers");

                int index = 0;
                while (index < acceptableAnswersArray.size()) {
                    String next = acceptableAnswersArray.get(index).get("acceptedAnswer" + (index + 1)).toString();
                    next = next.replace("\"", "");
                    ((FillInBlankAnswer) answer).addAlternativeAnswer(next);
                    index++;
                }

                question = new FillInBlankQuestion(questionName, questionText, answer);
            }

            lesson.addQuestion(question);
        }
    }
}
