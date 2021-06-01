package com.example.myapplication.model.levels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.QuizActivity;
import com.example.myapplication.R;
import com.example.myapplication.StartingScreen;

public class endActivity extends AppCompatActivity {
    private TextView textScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        textScore = findViewById(R.id.text_score);                                                                                                                                                                                                                              ScoreUpdate.setScore(2);
        textScore.setText("You finished with score: " + ScoreUpdate.getScore() );
        System.out.println("vf1" + ScoreUpdate.getScore());


        Button buttonStartTest = (Button)findViewById(R.id.restart_button);
        buttonStartTest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startTest();
            }
        });
    }
    private void startTest(){
        Intent intent = new Intent(this, StartingScreen.class);
        startActivity(intent);
    }



}
