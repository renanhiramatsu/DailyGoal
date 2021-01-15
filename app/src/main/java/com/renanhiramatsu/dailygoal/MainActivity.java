package com.renanhiramatsu.dailygoal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private Button start;
    private TextView countText;
    private CountDownTimer countDownTimer;
    //Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

    private long timeLeftInMs = 600000;
    private long savedTimeLeft;
    private boolean isRunning;

    int seconds = (int) (timeLeftInMs / 1000) % 60 ;
    int minutes = (int) ((timeLeftInMs / (1000*60)) % 60);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.button_Start);
        countText = findViewById(R.id.countDown);


    }

    public void clickStart(View v){
        if (!isRunning){

            countDownTimer = new CountDownTimer(timeLeftInMs, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    countText.setText(String.format("%d:%d",
                            TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                }

                @Override
                public void onFinish() {    // When timer ends.
                    isRunning = false;
                    // vib.vibrate(500);
                }
            }.start();
            isRunning = true;
        }else{      // If clicked while timer is running (while == pause)
            Toast t = Toast.makeText(this, "Timer is running, please stop first.", Toast.LENGTH_SHORT);
            t.show();
        }

    }

    public void clickReset(View v){
        if (isRunning){
            countDownTimer.cancel();
            countText.setText("10:00");
            isRunning = false;
        } else
        {
            Toast t = Toast.makeText(this, "Timer is already stopped.", Toast.LENGTH_SHORT);
            t.show();
        }
        //countText.setText("10:00");
    }
