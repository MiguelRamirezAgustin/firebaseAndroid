package com.example.myfirebaseauth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    //variables
    public static final String user="name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String user =getIntent().getStringExtra("name");

       // Toast.makeText(this," "+ user,Toast.LENGTH_SHORT).show();
    }
}
