//package com.paradigm.paradigm;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//
//public class Questions {
//    private boolean isCorrect = false;
//    private String question = "";
//    private String answer = "";
//
//    Questions(){}   //Default Constructor
//
//    Questions(String q, String a) {    //Constructor
//        question = q;
//        answer = a;
//    }
//
//    public String getQuestion(){
//        return question;
//    }   //Returns the question
//
//    public String getAnswer(){
//        return answer;
//    }   //Returns the answer
//
//    public boolean getStatus() {
//        return isCorrect;
//    }   //Shows if the question has been answered correctly
//
//    public void setQuestion(String q){
//        question = q;
//    }   //Sets the question
//
//    public void setAnswer(String a){
//        answer = a;
//    }   //Sets the answer
//
//    public boolean isCorrect(String correctAnswer){
//        return answer.equals(correctAnswer);
//    }   //Checks user entered answer with actual answer
//
//    public void changeFlag(){
////        if(!isCorrect) {
////            isCorrect = true;
////        }else{
////            isCorrect = false;
////        }
//        isCorrect = isCorrect;
//    }   //Changes the flag to true or false.
//
//    public void printOutput() {    //Prints the question and answer.
////        System.out.print(getQuestion()+"\r\n");
////        System.out.print(getAnswer()+"\r\n");
//        System.out.println(getQuestion());
//        System.out.println(getAnswer());
//    }
//
//    public void load(String fileName) {     //Loads Question from file.
//        File f = new File(fileName + ".txt");
//        StringBuilder fileQuestions = new StringBuilder();
//        try{
//            //Opens and reads from file.
//            BufferedReader br = new BufferedReader((new FileReader(f.getAbsolutePath())));
//            String line;
//            while ((line=br.readLine()) != null) {
//                fileQuestions.append(line + "\n");
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("File not found.");
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Couldn't read from file.");
//        }
//
//        String questLine = fileQuestions.toString();
//        String[] temp = new String[10];
//        for(int i=0; i<10; i++) {
//            temp[i] = "";
//        }
//
//        int wordIndex=0;
//        for(int i=0; i<questLine.length(); i++){
//            //Assigns all contents to str array.
//            if(questLine.charAt(i)==' '){
//                wordIndex++;
//            }
//            temp[wordIndex]+=questLine.charAt(i);
//        }
//
//        this.setQuestion(temp[0]);
//        this.setAnswer(temp[1]);
//
//    }
//
//    public static void main(String args[]){     //Test function
////        Questions q=new Questions();
////        q.setQuestion("Is this Patrick?\r\n");
////        System.out.print(q.getQuestion());
////        q.setAnswer("No, this is the Krusty Krabs\r\n");
////        System.out.print(q.getAnswer());
////        System.out.print(q.isCorrect("Hello")+"\n");
//        Questions p=new Questions("Hello there", "General Kenobi");
//        p.load("questions");
//        System.out.print(p.getQuestion().replace('-', ' ')+"\n");
//        System.out.print(p.getAnswer()+"\n");
//    }
//}
//
