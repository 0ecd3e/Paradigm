package com.paradigm.paradigm;

import android.content.Context;
import android.content.res.AssetManager;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paradigm.paradigm.exercises.question.Question;
import com.paradigm.paradigm.profile.UserProfile;
import com.paradigm.paradigm.profile.UserProgress;
import com.paradigm.paradigm.text.ContentModule;
import com.paradigm.paradigm.text.Course;
import com.paradigm.paradigm.text.Lesson;
import com.paradigm.paradigm.text.io.ContentLoader;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.junit.Assert.fail;

@SuppressWarnings("UnusedAssignment")
@RunWith(AndroidJUnit4.class)
public class UserProfileTest {
    @Test
    public void testUserProfile() {
        Course java = new Course("test");
        AssetManager assetManager = InstrumentationRegistry.getInstrumentation().getTargetContext().getResources().getAssets();

        InputStream inputStream;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            inputStream = assetManager.open("courses/Java Basics/courseJava.json");
            java = objectMapper.readValue(inputStream, Course.class);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ContentLoader contentLoader = new ContentLoader();
        contentLoader.loadDescription(java, assetManager);

        for (ContentModule module : java.getModules()) {
            contentLoader.loadDescription(module, assetManager);
            for (Lesson lesson : module.getLessons()) {
                //contentLoader.loadQuestions(lesson, assetManager);
                contentLoader.loadDescription(lesson, assetManager);
                contentLoader.loadLessonContent(lesson, assetManager);
                lesson.setParents();
            }
        }

        UserProgress userProgress = new UserProgress();
        userProgress.addCourse(java);

        ContentModule theModule = java.getModules().get(0);
        Lesson lesson = theModule.getLessons().get(0);
        Question question = lesson.getQuestions().get(0);
        userProgress.markQuestionCorrect(question);

        UserProfile userProfile = new UserProfile("testUser");
        userProfile.setUserProgress(userProgress);

        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        System.out.println(context.getFilesDir());

        try (FileOutputStream fos = context.openFileOutput("userProfile.ser", Context.MODE_PRIVATE)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
            objectOutputStream.writeObject(userProfile);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testLoadUserProfile() {

        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        UserProfile userProfile = new UserProfile("jfalksdjfla;sd");

        try {
            FileInputStream fis = context.openFileInput("userProfile.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fis);
            userProfile = (UserProfile) objectInputStream.readObject();
            objectInputStream.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            // Error occurred when opening raw file for reading.
            e.printStackTrace();
        }

    }

}
