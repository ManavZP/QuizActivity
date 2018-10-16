package com.example.quizactivity;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    
    private TextView questionText;
    
    private Button trueSelect;
    private Button falseSelect;

    private Quiz quiz;
    //private int i = 0;

    private int scoreNumber;
    
    private TextView score;

    public static final String TAG = "MainActivity";



    //private String q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        
        wireWidgets();
        setOnClickListeners();
        InputStream XmlFileInputStream = getResources().openRawResource(R.raw.questions);
        String jsonString = readTextFile(XmlFileInputStream);
        Gson gson = new Gson();
        Question[] questions =  gson.fromJson(jsonString, Question[].class);
        List<Question> questionList = Arrays.asList(questions);
        Log.d(TAG, "onCreate: " + questionList.toString());
        quiz = new Quiz(questionList);
        updateDisplay(3);


    }



    private void updateDisplay(int winOrLose) {
        if(winOrLose != 3){
            if(winOrLose == 1){
                scoreNumber++;
                quiz.scoreNum++;
            }}

        String scoreString = "Score: ";
        score.setText(scoreString + quiz.scoreNum);
        if(quiz.nextQExsist() == true) {
            questionText.setText(quiz.nextQuestion().toString());
        }
        else{
            endQuiz();
        }


    }

    private void endQuiz() {
        Toast.makeText(this, "QUIZ IS OVERRRR", Toast.LENGTH_SHORT).show();
    }







    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }




    private void setOnClickListeners() {
        trueSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quiz.getQuestion().getAnswer() == true){
                    updateDisplay(1);
                }
                else{
                    updateDisplay(0);
                }
            }
        });

        falseSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quiz.getQuestion().getAnswer() == false){
                    Toast.makeText(QuizActivity.this, "CHECKKCKEKKC", Toast.LENGTH_SHORT).show();
                    updateDisplay(1);
                }
                else{
                    updateDisplay(0);
                }

            }
        });
    }

    private void wireWidgets() {
        
        questionText = findViewById(R.id.textView_quiz_question);
        trueSelect = findViewById(R.id.button_quiz_true);
        falseSelect = findViewById(R.id.button_quiz_false);
        score = findViewById(R.id.textView_quiz_score);
        
    }
}
