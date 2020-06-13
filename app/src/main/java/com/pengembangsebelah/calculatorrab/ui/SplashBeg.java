package com.pengembangsebelah.calculatorrab.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.pengembangsebelah.calculatorrab.MainActivity;
import com.pengembangsebelah.calculatorrab.R;
import com.pengembangsebelah.calculatorrab.splash;

public class SplashBeg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_beg);
        new CountDownTimer(3000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent i = new Intent(SplashBeg.this, splash.class);
                startActivity(i);
                finish();
            }
        }.start();
    }
}
