package com.example.quizactivity;

import java.util.ArrayList;
import java.util.List;

public class Quiz {



    private List<Question> actualQuiz = new ArrayList<Question>();

    private int scoreNum = 0;
    private int qNum = -1;

    public Quiz(List<Question> listoq){

        actualQuiz = listoq;
        //getQuestion(1);


    }

    public int getScoreNum() {
        return scoreNum;
    }
    public void resetScore(){scoreNum = 0;}

    public void resetGame(){
        resetScore();

    }

    public void increaseScore(){
        scoreNum++;
    }

    public void setQuestion(int i){qNum = i;}
    public Question getQuestion() {
        return actualQuiz.get(qNum);
    }
    public Question getNextQuestion() {
        return actualQuiz.get(qNum+1);
    }


//    public void updateQuiz(int winOrLose){
//        if(winOrLose != 3){if(winOrLose == 1){scoreNum++;}}
//
//        String scoreString = "Score: ";
//        score.setText(scoreString + scoreNum);
//        if(nextQExsist() == true) {
//            nextQuestion();
//        }
//        else{
//            endQuiz();
//        }
//
//    }

    public Question nextQuestion() {
        qNum++;
        return actualQuiz.get(qNum);

    }


    public boolean nextQExsist() {
        //use the qnum variable to decide if there are more left and the List
        if(qNum == actualQuiz.size()-1){
            return false;
        }
        else{
            return true;
        }

    }

    // in main activity
    // if nextQExist, then call nextQuestion and set the text




}
