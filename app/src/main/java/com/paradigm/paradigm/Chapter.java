package com.example.myapplication;

import java.util.Vector;

public class Chapter {
    private double progress;
    private Vector<Sections> arr=new Vector<>();
    private int numSections=0;

    public Chapter(){}  //Default constructor

    public double getProgress(){    //Returns progress.
        return progress;
    }

    public int getSize(){   //Returns num of sections
        return numSections;
    }

    public void updateProgress(){   //Updates the progress percentage completed.
        int count=0;
        for(int i=0; i<arr.size(); i++){
            if(arr.get(i).isComplete()){
                count++;
            }
        }
        progress=(double) count/numSections;
    }

    public boolean isComplete(){    //Checks if chapter has been completed
        return (int) progress==numSections;
    }

    public void pushSection(Sections s){    //Adds a section to end of chapters
        arr.add(s);
        numSections=arr.size();
    }

    public void popSection(){   //Removes last section in vector
        arr.removeElementAt(arr.size()-1);
        numSections=arr.size();
    }

    public Sections get(int index){     //Retrieves section at index.
        return arr.get(index);
    }

    public void set(Sections s, int index){     //Sets element at index to s
        arr.setElementAt(s,index);
        numSections=arr.size();
    }

    public static void main(String args[]){     //Test function
        Chapter c=new Chapter();
        System.out.print(c.getSize()+"\r\n");
        Sections s1=new Sections();
        c.pushSection(s1);
        System.out.print(c.getSize()+"\r\n");
        Sections s2=new Sections();
        c.pushSection(s2);
        System.out.print(c.getSize()+"\r\n");
        System.out.print(c.getProgress()+"\r\n");
        System.out.print(c.isComplete()+"\r\n");
        System.out.print(c.get(0));
    }
}
