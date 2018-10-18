package com.example.quizactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ScoreActivity extends AppCompatActivity {

    private Button playAgain;

    private TextView finalScore;

    private TextView chastise;


    //public static final String EXTRA_SCORE = "score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        wireWidgets();
        setOnClickListeners();

        int scoreThing = getIntent().getIntExtra(QuizActivity.EXTRA_SCORE, 0);
        finalScore.setText( getString(R.string.score_scoreText) + scoreThing);
        
        setDes(scoreThing);
    }

    private void setOnClickListeners() {
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    private void setDes(int score) {
//        try {
//            score = Integer.parseInt(scoreThing);
//        } catch(NumberFormatException nfe) {}

        if(score == 10){
            chastise.setText("Wow ok, a perfect score...Yeah you probably cheated");
        }
        else if(score == 9){
            chastise.setText("Ooooh a 9. Just short of victory. You must be severely disappointed in yourself. I know I am.");
        }
        else if(score == 8){
            chastise.setText("Lol, an 8? An 8 sideways is infinity. Why didn't you get a score of infinity, you failure?");
        }
        else if(score == 7){
            chastise.setText("Yikes, a 7. That's like ");
        }
        else if(score == 6){
            chastise.setText("Haha, a 6. Thats terrible.");
        }
        else if(score == 5){
            chastise.setText("Hm, a 5. Half score. 50%. In school, that's an F. You failed.");
        }
        else if(score == 4){
            chastise.setText("A 4/5 is good. Too bad you got a 4/10.");
        }
        else if(score == 3){
            chastise.setText("A 3...Lemme guess, you got all the joke questions right. And all the knowledge ones wrong.");
        }
        else if(score == 2){
            chastise.setText("God, a 2 is an awful score. You are awful at this. Please stop playing, holy.");
        }
        else if(score == 1){
            chastise.setText("I have no words for your score of 1. I am ashamed to have you as a player.");
        }
        else if(score == 0){
            chastise.setText("HOW DID YOU GET A 0?? ONE OF THE QUESTIONS TOLD YOU WHICH BUTTON TO CLICK!?!?");
        }
        else if(score == -1){
            chastise.setText("If you get this score, email me at goldengasher9@gmail.com because it means I have made a grave error in my code");
        }
    }

    private void wireWidgets() {
        playAgain = findViewById(R.id.button_score_playAgain);
        finalScore = findViewById(R.id.textView_score_score);
        chastise = findViewById(R.id.textView_score_des);
    }
}
