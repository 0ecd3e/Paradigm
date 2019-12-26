package com.paradigm.paradigm;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

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

//    @Test
//    public void test() throws FileNotFoundException {
//        Course test = new Course("testName");
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        InputStream inputStream = InstrumentationRegistry.getInstrumentation().getContext().openFileInput("/src/main/assets/course1.json");
//        try {
//            test = objectMapper.readValue(inputStream, Course.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
}
