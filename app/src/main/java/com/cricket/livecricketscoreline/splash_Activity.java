package com.cricket.livecricketscoreline;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class splash_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_);


        final Handler handler  = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(splash_Activity.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        };

        handler.postDelayed(runnable, 3500);

    }
}
