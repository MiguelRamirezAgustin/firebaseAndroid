package com.example.myfirebaseauth;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class Splass extends AppCompatActivity {

    private  final int Duration = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splass);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splass);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            Intent intent = new Intent(Splass.this, MainActivity.class);
            startActivity(intent);
            finish();
            };
        },Duration );
    }
}
