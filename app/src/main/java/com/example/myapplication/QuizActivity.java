package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.model.JsonApi;
import com.example.myapplication.model.Post;
import com.example.myapplication.model.levels.ScoreUpdate;
import com.example.myapplication.model.levels.q1activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.media.CamcorderProfile.get;

public class QuizActivity extends AppCompatActivity {
    RadioGroup rg;
    RadioButton rb;
    private TextView textViewResult;
    private TextView textScore;
    private static final long COUNTDOWN_IN_MILLIS = 30000;
    public int scor = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);
        rg = (RadioGroup) findViewById(R.id.radio_group);
        textViewResult = findViewById(R.id.text_question);
        textScore = findViewById(R.id.text_score);
        ScoreUpdate.setScore(scor);
        textScore.setText("Scor:" + ScoreUpdate.getScore());


        //get retrofit implementation
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.171:8080/")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

        JsonApi jsonApi = retrofit.create(JsonApi.class);

        Call<List<Post>> call = jsonApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code" + response.code());
                    return;
                }
                List<Post> posts = response.body();
                    for (Post post : posts){
                        String content = "";
                        content+= post.getId() + "\n";
                        content += post.getEnunt() +"\n";
                        content += post.getVariante() +"\n";
                        content += post.getVarianta_corecta() +"\n\n";
                        //textViewResult.append(content);
                    }

                    Post problem1 = posts.get(0);
                   textViewResult.setText(problem1.getEnunt());
                   List<String> varianteRaspuns = varianteSplit(problem1.getVariante());

                 String[] text = {""};

                    for (int i = 0; i < varianteRaspuns.size(); i++) {
                        RadioButton radioButton = new RadioButton(getApplicationContext());
                        radioButton.setText(varianteRaspuns.get(i));
                        //radioButton.setId(post.getId());
                        radioButton.setTextColor(Color.BLACK);
                        rg.addView(radioButton);
                        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId)
                            {
                                RadioButton checkedRadioButton = (RadioButton) findViewById(checkedId);
                                 text[0] = checkedRadioButton.getText().toString();

                                Toast.makeText(getApplicationContext(), text[0], Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    System.out.println(text[0]);
                Button confirmSwitchActivity = (Button)findViewById(R.id.confirm_button);
                confirmSwitchActivity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //createPost(text[0]);

                        if(problem1.getVarianta_corecta().equals(text[0])) {
                            System.out.println(problem1.getVarianta_corecta());
                            System.out.println(text[0]);
                            Toast.makeText(getApplicationContext(), "Corect!", Toast.LENGTH_SHORT).show();
                            ScoreUpdate.updateScore();
                            textScore.setText("Score:" + ScoreUpdate.getScore());
                            System.out.println(ScoreUpdate.getScore());
                        }


                            else { Toast.makeText(getApplicationContext(), "Gresit...", Toast.LENGTH_SHORT).show();
                                    ScoreUpdate.setScore(0);}
                        switchActivities();
                        System.out.println(text[0]);
                    }
                });

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });


    }

    //post method


    private void switchActivities() {
        Intent confirmSwitchIntent = new Intent(this, q1activity.class);
        startActivity(confirmSwitchIntent);
    }

    public List<String> varianteSplit(String variante) {
        List<String> varianteArray = new ArrayList<String>();
        varianteArray = Arrays.asList(variante.split(","));
        return varianteArray;
    }


    //retrofit post
    /*public void createPost(String raspuns){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.171:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonApi jsonApi =  retrofit.create(JsonApi.class);
        Call<List<String>> call = jsonApi.createPost(raspuns);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(!response.isSuccessful()){
                    System.out.println(response.code());
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                System.out.println("not okay");
            }
        });
    }*/

}



