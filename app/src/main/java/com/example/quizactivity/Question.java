package com.example.quizactivity;

public class Question {


    private String question;
    private boolean answer;

    public Question(){
    }

    public boolean getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return question;
    }
}
