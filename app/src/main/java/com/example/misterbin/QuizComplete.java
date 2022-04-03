package com.example.misterbin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

public class QuizComplete extends AppCompatActivity {

    private ImageButton quizMainButton1;
    private Button quizMainButton2;
    private int reward_points, total_questions, wrong_answers_count, correct_answers_count;
    private float percentage;
    private TextView tv_reward_points, tv_question_number, tv_correct_count, tv_incorrect_count, tv_correct_percentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_complete);

        tv_reward_points = findViewById(R.id.reward_points_text);
        tv_question_number = findViewById(R.id.question_number);
        tv_correct_count = findViewById(R.id.correct_answer);
        tv_incorrect_count = findViewById(R.id.incorrect_answer);
        tv_correct_percentage = findViewById(R.id.marks_percentage);

        //Initialize and Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.nav_education);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_camera:
                        startActivity(new Intent(getApplicationContext(), Camera.class));
                        finish();
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_education:
                        startActivity(new Intent(getApplicationContext(), Education.class));
                        finish();
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        finish();
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        quizMainButton1 = findViewById(R.id.back_button);
        quizMainButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizComplete.this, QuizMain.class);
                startActivity(intent);
                finish();
            }
        });

        quizMainButton2 = findViewById(R.id.quiz_main_button);
        quizMainButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizComplete.this, QuizMain.class);
                startActivity(intent);
                finish();
            }
        });

        // Retrieve data passed from the quiz questions pages
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            reward_points = extras.getInt("reward_points");
            total_questions = extras.getInt("total_questions");
            wrong_answers_count = extras.getInt("wrong_answers_count");
            correct_answers_count = total_questions - wrong_answers_count;
            // Cast operator here has a higher precedence than the division operator
            percentage = (float)  100 * correct_answers_count/ total_questions;

            tv_reward_points.setText("You're rewarded with +" + reward_points + " points!");
            tv_question_number.setText(total_questions + " questions");
            tv_correct_percentage.setText((int) percentage + "%");
            tv_correct_count.setText("" + correct_answers_count);
            tv_incorrect_count.setText("" + wrong_answers_count);
        }


        // Network stuff
        String URL = "https://mobiledevuniassign.herokuapp.com/add_points/";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        SharedPreferences prefs = getSharedPreferences("PrefsFile", MODE_PRIVATE);
        String email = prefs.getString("email", null);

        JSONObject postData = new JSONObject();
        try {
            postData.put("email", email);
            postData.put("points", reward_points);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("REST Response", "AAAAAAAAAA " + "error with JSON");
        }

        // Request a JSON response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                URL,
                postData,
                response -> {
                    Log.e("REST Response", "AAAAAAAAAA " + response);
                },
                error -> {
                    Log.e("REST Response", "AAAAAAAAAA " + error.toString());
                    Toast.makeText(getApplicationContext(), "Error. Server didn't update your rewards.", Toast.LENGTH_LONG).show();
                }
        );

        requestQueue.add(jsonObjectRequest);
    }
}