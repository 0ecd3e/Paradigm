package com.paradigm.paradigm.text;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ContentLoader {
    public void loadContent(Content content, AssetManager assetManager) {
        String contentPath = content.storedName();
        String textContent;

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
            content.setDescription(textContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
