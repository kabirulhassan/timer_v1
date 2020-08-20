package com.example.timer_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView countdownText;
    private Button countdownButton;
    private CountDownTimer countDownTimer;
    private long timeleftinmilliseconds = 5000;//5 seconds for test
    private boolean timerRunning;
    private Button resetTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countdownButton = findViewById(R.id.countdown_button);
        countdownText = findViewById(R.id.countdown_text);
        resetTimer = findViewById(R.id.reset_button);

        resetTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });

        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStop();
            }
        });
        updateTimer();
    }
    public void startStop()
    {
        if(timerRunning)
            stopTimer();
        else
            startTimer();
    }
    public void resetTimer()
    {
        stopTimer();
        timeleftinmilliseconds=5000;
        updateTimer();
    }
    public void startTimer()
    {
        countdownButton.setText("Pause");
        countDownTimer = new CountDownTimer(timeleftinmilliseconds, 1000) {
            @Override
            public void onTick(long l) {
                timeleftinmilliseconds = l;
                updateTimer();
            }

            @Override
            public void onFinish() {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(500);
            }
        }.start();
        timerRunning = true;
    }
    public void stopTimer()
    {
        countdownButton.setText("Start");
        countDownTimer.cancel();
        timerRunning = false;
    }

    public void updateTimer()
    {
        int minutes = (int)timeleftinmilliseconds/60000;
        int seconds = (int)timeleftinmilliseconds%60000/1000;

        String timeLeft="";
        timeLeft=timeLeft+minutes+":";
        if(seconds<10)
            timeLeft+="0";
        timeLeft+=seconds;
        countdownText.setText(timeLeft);
    }
}