package com.paradigm.paradigm.text.io;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.paradigm.paradigm.exercises.answer.Answer;
import com.paradigm.paradigm.exercises.answer.FillInBlankAnswer;
import com.paradigm.paradigm.exercises.question.Question;
import com.paradigm.paradigm.text.ContentModule;
import com.paradigm.paradigm.text.Course;
import com.paradigm.paradigm.text.Lesson;

import java.io.IOException;
import java.util.List;

public class CourseSerializer extends StdSerializer<Course> {

    public CourseSerializer() {
        this(Course.class);
    }

    public CourseSerializer(Class<Course> courseClass) {
        super(courseClass);
    }


    @Override
    public void serialize(Course course, JsonGenerator gen, SerializerProvider provider) throws IOException {
        String courseName = course.getName();
        gen.writeStringField("name", courseName);

        gen.writeObjectFieldStart("modules"); //1
        for (ContentModule moduleEntry : course.getModules()) {
            String contentModuleName = moduleEntry.getName();
            gen.writeObjectFieldStart(contentModuleName); //2
            gen.writeStringField("parentCourse", courseName);
            //gen.writeStringField("name", contentModuleName);

            gen.writeObjectFieldStart("lessons"); //3
            List<Lesson> lessonEntrySet = moduleEntry.getLessons();
            for (Lesson lessonEntry : lessonEntrySet) {
                gen.writeObjectFieldStart(lessonEntry.getName()); //4
                gen.writeStringField("parentCourse", courseName);
                gen.writeStringField("parentContentModule", contentModuleName);
                //gen.writeStringField("name", lessonEntry.getValue().getName());

                handleQuestions(gen, courseName, lessonEntry, contentModuleName);

                gen.writeEndObject(); //4
            }
            gen.writeEndObject(); //3


            gen.writeEndObject(); //2
        }
        gen.writeEndObject(); //1
    }

    private void handleQuestions(JsonGenerator gen, String courseName, Lesson lesson, String contentModuleName) throws IOException {
        gen.writeObjectFieldStart("questions"); //3
        List<Question> questionEntrySet = lesson.getQuestions();
        for (Question questionEntry : questionEntrySet) {
            String questionType = questionEntry.getQuestionType();

            gen.writeObjectFieldStart(questionEntry.getQuestionName()); //4
            gen.writeStringField("type", questionType);
            gen.writeStringField("parentCourse", courseName);
            gen.writeStringField("parentContentModule", contentModuleName);
            gen.writeStringField("parentLesson", lesson.getName());
            //gen.writeStringField("name", question.getQuestionName());
            gen.writeStringField("text", questionEntry.getQuestionText());

            gen.writeObjectFieldStart("answer"); //5
            Answer answer = questionEntry.getAnswer();
            if (answer.getAnswerType().equals("multipleChoiceAnswer")) {
                gen.writeStringField("bestAnswer", answer.getAnswer());
            } else {
                FillInBlankAnswer fillInBlankAnswer = (FillInBlankAnswer) answer;
                gen.writeStringField("bestAnswer", answer.getAnswer());
                gen.writeArrayFieldStart("acceptedAnswers"); //6
                int answerIndex = 1;
                for (String acceptedAnswer : fillInBlankAnswer.getAcceptedAnswers()) {
                    gen.writeStartObject(); //7
                    gen.writeStringField("acceptedAnswer" + answerIndex, acceptedAnswer);
                    answerIndex++;
                    gen.writeEndObject(); //7
                }
                gen.writeEndArray(); //6
            }

            gen.writeEndObject(); //5

            gen.writeEndObject(); //4
        }
        gen.writeEndObject(); //3
    }

    @Override
    public void serializeWithType(Course value, JsonGenerator gen,
                                  SerializerProvider provider, TypeSerializer typeSer)
            throws IOException {

        WritableTypeId typeId = typeSer.typeId(value, JsonToken.START_OBJECT);
        typeSer.writeTypePrefix(gen, typeId);
        serialize(value, gen, provider);
        typeSer.writeTypeSuffix(gen, typeId);
    }
}
