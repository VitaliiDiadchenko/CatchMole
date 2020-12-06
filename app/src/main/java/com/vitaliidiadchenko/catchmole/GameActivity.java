package com.vitaliidiadchenko.catchmole;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class GameActivity extends AppCompatActivity {
    private ImageView mole1;
    private ImageView mole2;
    private ImageView mole3;
    private ImageView mole4;
    private TextView textScore;
    private TextView timer;
    /*private boolean gameOver = false;*/
    private int rightAnswer;
    private int countOfRightAnswers = 0;
    private int countOfQuestions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mole1 = findViewById(R.id.imageViewMole1);
        mole2 = findViewById(R.id.imageViewMole2);
        mole3 = findViewById(R.id.imageViewMole3);
        mole4 = findViewById(R.id.imageViewMole4);
        textScore = findViewById(R.id.textViewScore);
        timer = findViewById(R.id.textViewTimer);
        playGame();
        CountDownTimer countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(getTime(millisUntilFinished));
                if(millisUntilFinished < 10000) {
                    timer.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                }
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        };
        countDownTimer.start();
    }

    private void playGame() {
        generateQuestion();
        String score = String.format("%s / %s", countOfRightAnswers, countOfQuestions);
        textScore.setText(score);
    }

    public void generateQuestion() {
        rightAnswer = (int) (Math.random() * 4);
        if (rightAnswer == 0) {
            mole1.setVisibility(View.VISIBLE);
            mole2.setVisibility(View.INVISIBLE);
            mole3.setVisibility(View.INVISIBLE);
            mole4.setVisibility(View.INVISIBLE);
        }
        if (rightAnswer == 1) {
            mole1.setVisibility(View.INVISIBLE);
            mole2.setVisibility(View.VISIBLE);
            mole3.setVisibility(View.INVISIBLE);
            mole4.setVisibility(View.INVISIBLE);
        }
        if (rightAnswer == 2) {
            mole1.setVisibility(View.INVISIBLE);
            mole2.setVisibility(View.INVISIBLE);
            mole3.setVisibility(View.VISIBLE);
            mole4.setVisibility(View.INVISIBLE);
        }
        if (rightAnswer == 3) {
            mole1.setVisibility(View.INVISIBLE);
            mole2.setVisibility(View.INVISIBLE);
            mole3.setVisibility(View.VISIBLE);
            mole4.setVisibility(View.INVISIBLE);
        }
        countOfQuestions++;
    }

    private String getTime(long millis) {
        int seconds = (int) millis / 1000;
        int minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
    }

    public void onClickAnswer0(View view) {
        if (rightAnswer == 0) {
            countOfRightAnswers++;
        }
        playGame();
    }

    public void onClickAnswer1(View view) {
        if (rightAnswer == 1) {
            countOfRightAnswers++;
        }
        playGame();
    }

    public void onClickAnswer2(View view) {
        if (rightAnswer == 2) {
            countOfRightAnswers++;
        }
        playGame();
    }

    public void onClickAnswer3(View view) {
        if (rightAnswer == 3) {
            countOfRightAnswers++;
        }
        playGame();
    }
}