package com.paradigm.paradigm;

import android.content.Context;
import android.content.res.AssetManager;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paradigm.paradigm.exercises.question.Question;
import com.paradigm.paradigm.profile.UserProgress;
import com.paradigm.paradigm.text.ContentModule;
import com.paradigm.paradigm.text.Course;
import com.paradigm.paradigm.text.Lesson;
import com.paradigm.paradigm.text.io.ContentLoader;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.paradigm.paradigm", appContext.getPackageName());
    }

    @Test
    public void testLoad() {
        Course java = new Course("test");
        AssetManager assetManager = InstrumentationRegistry.getInstrumentation().getTargetContext().getResources().getAssets();

        InputStream inputStream = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            inputStream = assetManager.open("courses/java/courseJava.json");
            java = objectMapper.readValue(inputStream, Course.class);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ContentLoader contentLoader = new ContentLoader();
        contentLoader.loadDescription(java, assetManager);

        for (ContentModule module : java.getModules().values()) {
            contentLoader.loadDescription(module, assetManager);
            contentLoader.loadQuestions(module, assetManager);
            for (Lesson lesson : module.getLessons().values()) {
                contentLoader.loadDescription(lesson, assetManager);
                contentLoader.loadLessonContent(lesson, assetManager);
            }
        }

        UserProgress userProgress = new UserProgress();
        userProgress.addCourse(java);

        ContentModule theModule = java.getModules().get("module2");
        Question question = theModule.getQuestions().get("q3");
        userProgress.markQuestionCorrect(question);
    }
}
