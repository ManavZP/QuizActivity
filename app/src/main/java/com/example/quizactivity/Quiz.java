package com.example.quizactivity;

import java.util.ArrayList;
import java.util.List;

public class Quiz {



    private List<Question> actualQuiz = new ArrayList<Question>();

    public Quiz(List<Question> listoq){

        actualQuiz = listoq;
        getQuestion(1);


    }

    public Question getQuestion(int i) {
        return actualQuiz.get(i);
    }


}
