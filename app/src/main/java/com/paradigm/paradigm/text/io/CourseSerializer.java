package com.paradigm.paradigm.text.io;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
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
//        gen.writeStartObject();
        String courseName = value.getName();
        gen.writeStringField("name", courseName);

        gen.writeObjectFieldStart("modules");
        for (Map.Entry<String, ContentModule> moduleEntry : value.getModules().entrySet()) {
            String contentModuleName = moduleEntry.getValue().getName();
            gen.writeObjectFieldStart(moduleEntry.getKey());
            gen.writeStringField("name", contentModuleName);
            gen.writeStringField("parentCourse", courseName);

            gen.writeObjectFieldStart("lessons");
            Set<Map.Entry<String, Lesson>> lessonEntrySet = moduleEntry.getValue().getLessons().entrySet();
            for (Map.Entry<String, Lesson> lessonEntry: lessonEntrySet) {
                gen.writeObjectFieldStart(lessonEntry.getKey());
                gen.writeStringField("name", lessonEntry.getValue().getName());
                gen.writeStringField("parentContentModule", contentModuleName);
                gen.writeStringField("parentCourse", courseName);
                gen.writeEndObject();
            };
            gen.writeEndObject();

            gen.writeObjectFieldStart("questions");
            Set<Map.Entry<String, Question>> questionEntrySet = moduleEntry.getValue().getQuestions().entrySet();
            for (Map.Entry<String, Question> questionEntry: questionEntrySet) {
                gen.writeObjectFieldStart(questionEntry.getKey());
                gen.writeStringField("name", questionEntry.getValue().getQuestionName());
                gen.writeStringField("parentContentModule", contentModuleName);
                gen.writeStringField("parentCourse", courseName);
                gen.writeEndObject();
            };
            gen.writeEndObject();
            gen.writeEndObject();
        }
//        gen.writeEndObject();
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
