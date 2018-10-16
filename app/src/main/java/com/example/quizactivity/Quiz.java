package com.example.quizactivity;

import java.util.ArrayList;
import java.util.List;

public class Quiz {



    private List<Question> actualQuiz = new ArrayList<Question>();

    int scoreNum = 0;
    int qNum = -1;

    public Quiz(List<Question> listoq){

        actualQuiz = listoq;
        //getQuestion(1);


    }

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
        if(getNextQuestion() == null){
            return false;
        }
        else{
            return true;
        }

    }

    // in main activity
    // if nextQExist, then call nextQuestion and set the text




}
