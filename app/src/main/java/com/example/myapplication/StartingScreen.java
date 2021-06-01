package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class StartingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
       // getActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button buttonStartTest = findViewById(R.id.start_button);
        buttonStartTest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startTest();
            }
        });
    }

    private void startTest(){
        Intent intent = new Intent(StartingScreen.this, QuizActivity.class);
        startActivity(intent);
    }

}