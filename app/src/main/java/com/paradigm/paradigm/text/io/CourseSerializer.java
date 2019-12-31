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
import java.util.Map;
import java.util.Set;

public class CourseSerializer extends StdSerializer<Course> {

    public CourseSerializer() {
        this(Course.class);
    }

    public CourseSerializer(Class<Course> courseClass) {
        super(courseClass);
    }


    @Override
    public void serialize(Course value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        String courseName = value.getName();
        gen.writeStringField("name", courseName);

        gen.writeObjectFieldStart("modules"); //1
        for (Map.Entry<String, ContentModule> moduleEntry : value.getModules().entrySet()) {
            String contentModuleName = moduleEntry.getValue().getName();
            gen.writeObjectFieldStart(moduleEntry.getKey()); //2
            gen.writeStringField("parentCourse", courseName);
            gen.writeStringField("name", contentModuleName);

            gen.writeObjectFieldStart("lessons"); //3
            Set<Map.Entry<String, Lesson>> lessonEntrySet = moduleEntry.getValue().getLessons().entrySet();
            for (Map.Entry<String, Lesson> lessonEntry: lessonEntrySet) {
                gen.writeObjectFieldStart(lessonEntry.getKey()); //4
                gen.writeStringField("parentCourse", courseName);
                gen.writeStringField("parentContentModule", contentModuleName);
                gen.writeStringField("name", lessonEntry.getValue().getName());
                gen.writeEndObject(); //4
            };
            gen.writeEndObject(); //3

            gen.writeObjectFieldStart("questions"); //3
            Set<Map.Entry<String, Question>> questionEntrySet = moduleEntry.getValue().getQuestions().entrySet();
            for (Map.Entry<String, Question> questionEntry: questionEntrySet) {
                Question question = questionEntry.getValue();
                String questionType = question.getQuestionType();

                gen.writeObjectFieldStart(questionEntry.getKey()); //4
                gen.writeStringField("type", questionType);
                gen.writeStringField("parentCourse", courseName);
                gen.writeStringField("parentContentModule", contentModuleName);
                gen.writeStringField("name", question.getQuestionName());

                gen.writeObjectFieldStart("answer"); //5
                Answer answer = question.getAnswer();
                if (answer.getAnswerType().equals("multipleChoiceAnswer")) {
                    gen.writeStringField("bestAnswer", answer.getAnswer());
                } else {
                    FillInBlankAnswer fillInBlankAnswer = (FillInBlankAnswer) answer;
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
            };
            gen.writeEndObject(); //3

            gen.writeEndObject(); //2
        }
        gen.writeEndObject(); //1
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
