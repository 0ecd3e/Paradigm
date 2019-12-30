package com.paradigm.paradigm;

import android.content.Context;
import android.content.res.AssetManager;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paradigm.paradigm.profile.UserProgress;
import com.paradigm.paradigm.text.ContentLoader;
import com.paradigm.paradigm.text.ContentModule;
import com.paradigm.paradigm.text.Course;
import com.paradigm.paradigm.text.Lesson;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

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

        for (ContentModule module : java.getModules()) {
            contentLoader.loadDescription(module, assetManager);
            contentLoader.loadQuestions(module, assetManager);
            Set<Lesson> lessons = module.getLessons();
            for (Lesson lesson : lessons) {
                contentLoader.loadDescription(lesson, assetManager);
                contentLoader.loadLessonContent(lesson, assetManager);
            }
        }

        UserProgress userProgress = new UserProgress();
        userProgress.addCourse(java);
    }
}
