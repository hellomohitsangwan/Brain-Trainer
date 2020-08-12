package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;


public class MainActivity extends AppCompatActivity {
    int locationOfCorrectAnswer;
    Button startButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    TextView resultTextView ;
    int score  = 0;
    int numberOfQuesion = 0;
    TextView scoreTextView;

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;

    TextView sumTextView;
    TextView timerTextView ;

    public void playAgain(View view)  {

    score = 0;
    numberOfQuesion = 0;
    timerTextView.setText("30 S");
    button4.setVisibility(View.INVISIBLE);
        scoreTextView.setText(score + "/" + numberOfQuesion);
        resultTextView.setText("");
        newQuestion();
        new CountDownTimer(15000, 1000) {


            @Override
            public void onTick(long l) {

                timerTextView.setText(String.valueOf(l / 1000) + " S");

            }

            @Override
            public void onFinish() {
                resultTextView.setText("your have done " + score +" questions right out of " + numberOfQuesion + "questions" );
                button4.setVisibility(View.VISIBLE);
            }
        }.start();


    }

    public void chooseAnswer(View view) {
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            resultTextView.setText("CORRECT (:");
            Log.i("correct" , "you got it");
            score++; //bascically "score++" = "score += 1";

        } else {
            resultTextView.setText("INCORRECT ):");
            Log.i("incorrect" , "):");

        }
        numberOfQuesion++;

        scoreTextView.setText(score + "/" + numberOfQuesion);

        Log.i("tag : " , view.getTag().toString());
        newQuestion();

    }

//    public void start(View view) {
//
//        startButton.setVisibility(View.INVISIBLE);
//
//    }
//
    public void newQuestion () {
        Random rand = new Random();

        int a,b;
        a = rand.nextInt(21);
        b = rand.nextInt(21);
        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));
        locationOfCorrectAnswer = rand.nextInt(4);
         answers.clear();
        for(int i =0; i <4 ; i++) {
            if(i == locationOfCorrectAnswer) {
                answers.add(a+b);
            } else {
                int wrongAns = rand.nextInt(42);
                while (wrongAns == a+b) {
                    wrongAns = rand.nextInt(42);
                }
                answers.add(wrongAns);
            }



        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.start);
        sumTextView = findViewById(R.id.sumTextView);


        resultTextView  = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.playAgain);
        timerTextView = findViewById(R.id.timerTextView);
        playAgain(findViewById(R.id.timerTextView));



    }
}
