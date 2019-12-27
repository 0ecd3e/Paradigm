package com.paradigm.paradigm.text;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ContentLoader {
    public String loadFromAssets(String contentPath, AssetManager assetManager) {
        String textContent = "";

        try {
            InputStream inputStream = assetManager.open(contentPath);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            StringBuilder stringBuilder = new StringBuilder();

            try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                while ((textContent = bufferedReader.readLine()) != null) {
                    stringBuilder.append(textContent);
                }
            }

            textContent = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textContent;
    }

    public void loadDescription(Content content, AssetManager assetManager) {
        String descriptionPath = content.descriptionPath();
        String desc = loadFromAssets(descriptionPath, assetManager);
        content.setDescription(desc);
    }

    public void loadLessonContent(Lesson lesson, AssetManager assetManager) {
        String contentPath = lesson.lessonContentPath();
        String content = loadFromAssets(contentPath, assetManager);
        lesson.setLessonContent(content);
    }
}
