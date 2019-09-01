package com.example.myapplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Questions {
    private boolean correct_flag=false;
    private String question="";
    private String answer="";

    Questions(){}   //Default Constructor

    Questions(String s1, String s2){    //Constructor
        question=s1;
        answer=s2;
    }

    public String getQuestion(){
        return question;
    }   //Returns the question

    public String getAnswer(){
        return answer;
    }   //Returns the answer

    public boolean getFlag(){
        return correct_flag;
    }   //Shows if the question has been answered correctly

    public void setQuestion(String s){
        question=s;
    }   //Sets the question

    public void setAnswer(String s){
        answer=s;
    }   //Sets the answer

    public void updateCorrect(String s){
        if(s==answer){
            correct_flag=true;
        }
    }   //Checks user entered answer with actual answer

//    public void changeFlag(){
//        if(!correct_flag) {
//            correct_flag = true;
//        }else{
//            correct_flag=false;
//        }
//    }   //Changes the flag to true or false.

    public void print(){    //Prints the question and answer.
        System.out.print(getQuestion()+"\r\n");
        System.out.print(getAnswer()+"\r\n");
    }

    public void load(String path, String file){     //Loads Question from file.
        File f=new File(path+"\\"+file+".txt");
        StringBuilder fileQuestions=new StringBuilder();
        try{
            //Opens and reads from file.
            BufferedReader br=new BufferedReader((new FileReader(f.getAbsolutePath())));
            String line;
            while((line=br.readLine())!=null){
                fileQuestions.append(line+"\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.print("File not found.\n");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("Couldn't read from file.\n");
        }

        String questLine=fileQuestions.toString();
        String[] temp = new String[10];
        for(int i=0; i<10; i++) {
            temp[i] = "";
        }

        int wordIndex=0;
        for(int i=0; i<questLine.length(); i++){
            //Assigns all contents to str array.
            if(questLine.charAt(i)==' '){
                wordIndex++;
            }
            temp[wordIndex]+=questLine.charAt(i);
        }

        this.setQuestion(temp[0]);
        this.setAnswer(temp[1]);

    }

    public static void main(String args[]){     //Test function
//        Questions q=new Questions();
//        q.setQuestion("Is this Patrick?\r\n");
//        System.out.print(q.getQuestion());
//        q.setAnswer("No, this is the Krusty Krabs\r\n");
//        System.out.print(q.getAnswer());
//        System.out.print(q.isCorrect("Hello")+"\n");
        Questions p=new Questions("Hello there", "General Kenobi");
        p.load("For loops", "questions");
        System.out.print(p.getQuestion().replace('-', ' ')+"\n");
        System.out.print(p.getAnswer()+"\n");
    }
}

