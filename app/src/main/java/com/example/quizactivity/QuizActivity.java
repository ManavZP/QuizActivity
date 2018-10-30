package com.example.quizactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import android.os.Handler;


public class QuizActivity extends AppCompatActivity {
    
    private TextView questionText;
    
    private Button trueSelect;
    private Button falseSelect;

    private Quiz quiz;



    private TextView timerTextView;
    private int time = 20;


    private ImageView pausePlay;
    private TextView pause;

    private int scoreNumber;
    
    private TextView score;
    private boolean paused = false;

    public static final String TAG = "MainActivity";


    public static final String EXTRA_SCORE = "score";


    //private String q;


    private Handler timerHandler = new Handler();



    private Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {

            if(time != 0){
                time--;
                timerTextView.setText(" " + time);
                timerHandler.postDelayed(this, 1000);
            }
            else{
                finishAffinity();
            }

        }
    };

    //runs without a timer by reposting this handler at the end of the runnable


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        
        wireWidgets();
        setOnClickListeners();

        startTimer();


        InputStream XmlFileInputStream = getResources().openRawResource(R.raw.questions);
        String jsonString = readTextFile(XmlFileInputStream);
        Gson gson = new Gson();
        Question[] questions =  gson.fromJson(jsonString, Question[].class);
        List<Question> questionList = Arrays.asList(questions);
        Log.d(TAG, "onCreate: " + questionList.toString());
        quiz = new Quiz(questionList);
        updateDisplay(3);


    }

    private void startTimer() {


        timerHandler.postDelayed(timerRunnable, 1000);

    }


    private void updateDisplay(int winOrLose) {
        if(winOrLose != 3){
            if(winOrLose == 1){
                scoreNumber++;
                quiz.increaseScore();
            }}

        String scoreString = "Score: ";
        score.setText(scoreString + quiz.getScoreNum());
        if(quiz.nextQExsist() == true) {
            questionText.setText(quiz.nextQuestion().toString());
        }
        else{
            endQuiz();
        }


    }

//    public void resetQuiz(){
//        quiz.setQuestion(-1);
//        questionText.setText(quiz.getQuestion().toString());
//    }

    private void endQuiz() {
        //int tranfer = ;
        //create an intent

        quiz.setQuestion(-1);
        //questionText.setText(quiz.getQuestion().toString());

        int temp = quiz.getScoreNum();

        time=20;
        timerTextView.setText(" " + time);
        timerHandler.removeCallbacks(timerRunnable);
        paused = true;
        pause();


        quiz.resetScore();
        updateDisplay(3);

        Intent intentScore = new Intent(QuizActivity.this, ScoreActivity.class);
        //where you are coming and from where you are going

        intentScore.putExtra(EXTRA_SCORE, temp);
        //package that text into the intent

        //start the activity

        //startActivityForResult(intentRegister, REGISTER_CODE);

        startActivity(intentScore);
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
                if(paused == true){
                    timerHandler.postDelayed(timerRunnable, 1000);
                    paused = false;
                    unPause();
                }

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
                if(paused == true){
                    timerHandler.postDelayed(timerRunnable, 1000);
                    paused = false;
                    unPause();
                }
                if(quiz.getQuestion().getAnswer() == false){
                    //Toast.makeText(QuizActivity.this, "CHECKKCKEKKC", Toast.LENGTH_SHORT).show();
                    updateDisplay(1);
                }
                else{
                    updateDisplay(0);
                }

            }
        });

        pausePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paused == true){
                    timerHandler.postDelayed(timerRunnable, 1000);
                    paused = false;
                    unPause();
                }
                else{
                    timerHandler.removeCallbacks(timerRunnable);
                    paused = true;
                    pause();
                }
            }
        });
    }

    public void unPause(){
        trueSelect.setVisibility(View.VISIBLE);
        falseSelect.setVisibility(View.VISIBLE);
        questionText.setVisibility(View.VISIBLE);
        pause.setVisibility(View.GONE);
    }
    public void pause(){
        trueSelect.setVisibility(View.GONE);
        falseSelect.setVisibility(View.GONE);
        questionText.setVisibility(View.GONE);
        pause.setVisibility(View.VISIBLE);
    }

    private void wireWidgets() {
        
        questionText = findViewById(R.id.textView_quiz_question);
        trueSelect = findViewById(R.id.button_quiz_true);
        falseSelect = findViewById(R.id.button_quiz_false);
        score = findViewById(R.id.textView_quiz_score);


        timerTextView = findViewById(R.id.textView_quiz_timer);


        pausePlay = findViewById(R.id.imageView_quiz_pause);

        pausePlay.setColorFilter(this.getResources().getColor(R.color.maroon));
        pause = findViewById(R.id.textView_quiz_pauseText);
        pause.setVisibility(View.GONE);
        
    }
}
