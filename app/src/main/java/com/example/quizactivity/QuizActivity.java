package com.example.quizactivity;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
    
    private TextView question;
    
    private Button trueSelect;
    private Button falseSelect;
    
    private TextView score;

    public static final String TAG = "MainActivity";

    //private String q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        
        wireWidgets();
        setOnClickListeners();
       // writeText("text.txt", readQuestions("res/raw/questions.json"));

        InputStream XmlFileInputStream = getResources().openRawResource(R.raw.questions);
        String jsonString = readTextFile(XmlFileInputStream);
        // create a gson object
        Gson gson = new Gson();
// read your json file into an array of questions
        Question[] questions =  gson.fromJson(jsonString, Question[].class);
// convert your array to a list using the Arrays utility class
        List<Question> questionList = Arrays.asList(questions);
// verify that it read everything properly
        Log.d(TAG, "onCreate: " + questionList.toString());
    }






    private void writeText(String filename, String text) {
        File file = new File(filename);
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file, false);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.append(text);

            bufferedWriter.close();

        } catch (IOException e) {
          e.printStackTrace();
        }
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

            }
        });

        falseSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void wireWidgets() {
        
        question = findViewById(R.id.textView_quiz_question);
        trueSelect = findViewById(R.id.button_quiz_true);
        falseSelect = findViewById(R.id.button_quiz_false);
        score = findViewById(R.id.textView_quiz_score);
        
    }
}
