package com.example.myapplication;
import android.os.Environment;
import android.view.Display;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.*;
import java.util.*;

public class TextModule {
    private String text="";

    public TextModule(){}

    public TextModule(String s){
        text=s;
    }

    public String getText(){
        return text;
    }

    public void setText(String s){ text=s; }

    public String read(String s){
        File f=new File(s+".txt");
        StringBuilder fileText= new StringBuilder();
        try {
            BufferedReader br = new BufferedReader((new FileReader(f.getAbsolutePath())));
            String line;
            while ((line = br.readLine())!=null){
                fileText.append(line);
                fileText.append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.print("File not found.\n");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("Couldn't read from file.\n");
        }

        return fileText.toString();
    }

    public static void main(String args[]){
        TextModule t=new TextModule("For loops are very important and stuff");
        System.out.print(t.getText()+"\n");
        t.read("test");
        System.out.print(t.getText()+"\n");
    }
}
