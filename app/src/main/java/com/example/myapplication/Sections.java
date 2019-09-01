package com.example.myapplication;
import android.os.Environment;

import java.util.Vector;

public class Sections {
    private String secName="";
    private int numQuestions=0;
    private TextModule t;
    private Vector<Questions> questionsList=new Vector<>();

    public Sections(){}     //Default constructor

    public Sections(TextModule tm, Vector<Questions> v){    //Constructor
        t=tm;
        questionsList=v;
    }

    public Sections(String title){
        secName=title;
        String path= Environment.getDataDirectory().getAbsolutePath()+"/"+this.secName;
        System.out.print(path);
    }

    public String getName(){return secName;}

    public boolean isComplete(){     //Shows whether the Section has been completed.
        int count=0;
        for(int i=0; i<questionsList.size(); i++){
            //Traverses list and checks bool value.
            if(questionsList.get(i).getFlag()){
                //Increment if question has been flagged as finished
                count++;
            }
        }
        if(count==numQuestions) {   //Section is complete if all questions are finished
            return true;
        }
        return false;
    }

    public void push(Questions q){  //Adds element at end of vector
        questionsList.add(q);
        numQuestions=questionsList.size();  //Updates size
    }

    public void pop(){  //Removes last element from vector
        questionsList.removeElementAt(questionsList.size()-1);
        numQuestions=questionsList.size();  //Updates size
    }

    public TextModule getModule() {
        return t;
    }

    public void setModule(TextModule tm){
        t=tm;
    }

    public Questions get(int index){    //Retrieves element at index
        return questionsList.get(index);
    }

    public int getSize(){   //Returns number of questions in section
        return numQuestions;
    }

    public void set(Questions q, int index){    //Places element q at index.
        questionsList.setElementAt(q,index);
    }

    public static void main(String args[]){     //Test function
//        Sections s=new Sections();
//        System.out.print(s.getSize()+"\r\n");
//        Questions q=new Questions("Is this it?", "No it isn't.");
//        s.push(q);
//        System.out.print(s.getSize()+"\r\n");
//        Questions q2=new Questions("What are those", "I dunno");
//        s.push(q2);
//        System.out.print(s.getSize()+"\r\n");
//        Questions p=s.get(0);
//        p.print();
//        s.pop();
//        System.out.print(s.getSize()+"\r\n");
//        System.out.print(s.isComplete());

        Sections p=new Sections("For loops");
    }
}