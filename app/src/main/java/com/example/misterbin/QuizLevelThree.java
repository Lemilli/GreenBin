package com.example.misterbin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.misterbin.adapter.SuggestedArticlesAdapter;
import com.example.misterbin.model.QuestionData;
import com.example.misterbin.model.SuggestedArticlesData;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class QuizLevelThree extends AppCompatActivity {

    private ImageButton backButton;

    private ArrayList<QuestionData> questions = new ArrayList<QuestionData>();
    int questionNumber = 0;
    int reward_points = 150;
    int wrong_answers_count = 0;
    TextView tv_quiz_number, tv_quiz_question, tv_points;
    Button btn_quiz_choice1, btn_quiz_choice2, btn_quiz_choice3;

//    //This is for the horizontal recycler view scroll
//    RecyclerView suggestedArticlesRecycler;
//    SuggestedArticlesAdapter suggestedArticlesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_level_three);

        // Assign IDs
        tv_quiz_number = findViewById(R.id.quiz_number);
        tv_quiz_question = findViewById(R.id.quiz_question);
        btn_quiz_choice1 = findViewById(R.id.quiz_choice_one);
        btn_quiz_choice2 = findViewById(R.id.quiz_choice_two);
        btn_quiz_choice3 = findViewById(R.id.quiz_choice_three);
        tv_points = (TextView) findViewById(R.id.points_count);

//        //Initialize and Assign Variable
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
//
//        //Set Home Selected
//        bottomNavigationView.setSelectedItemId(R.id.nav_education);
//
//        //Perform ItemSelectedListener
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId())
//                {
//                    case R.id.nav_camera:
//                        startActivity(new Intent(getApplicationContext(), Camera.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//                    case R.id.nav_home:
//                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//                    case R.id.nav_education:
//                        startActivity(new Intent(getApplicationContext(), Education.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//                    case R.id.nav_profile:
//                        startActivity(new Intent(getApplicationContext(), Profile.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//                }
//                return false;
//            }
//        });

        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizLevelThree.this, QuizMain.class);
                startActivity(intent);
                finish();
            }
        });

        // REST API connection
        String URL = "https://mobiledevuniassign.herokuapp.com/quiz/";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Request a JSON response from the provided URL.
        JsonArrayRequest objectRequest = new JsonArrayRequest(
                URL,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            int level = jsonObject.getInt("level");
                            String question = jsonObject.getString("question");
                            String option1 = jsonObject.getString("option1");
                            String option2 = jsonObject.getString("option2");
                            String option3 = jsonObject.getString("option3");
                            String correct_answer = jsonObject.getString("correct_answer");

                            QuestionData questionData = new QuestionData(level, question, option1, option2, option3, correct_answer);
                            // add only questions for level 3
                            if (level == 3)
                                questions.add(questionData);
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Error loading terminals. Try again later.", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                    NextQuestion();
                    Log.e("REST Response", "AAAAAAAAAA " + response.toString());
                },
                error -> {
                    tv_quiz_question.setText("Error. Try again later.");
                    Toast.makeText(getApplicationContext(), "Error loading questions. Try again later.", Toast.LENGTH_LONG).show();
                }
        );

        requestQueue.add(objectRequest);

        // Done with network stuff
        // Adding button listeners
        btn_quiz_choice1.setOnClickListener(view -> {
            // if correct
            if (btn_quiz_choice1.getText().toString().equals(questions.get(questionNumber).correct_answer)) {
                Toast.makeText(QuizLevelThree.this, "Correct!", Toast.LENGTH_SHORT).show();
                questionNumber++;
                NextQuestion();
            } else {
                WrongAnswer();
            }
        });
        btn_quiz_choice2.setOnClickListener(view -> {
            // if correct
            if (btn_quiz_choice2.getText().toString().equals(questions.get(questionNumber).correct_answer)) {
                Toast.makeText(QuizLevelThree.this, "Correct!", Toast.LENGTH_SHORT).show();
                questionNumber++;
                NextQuestion();
            } else {
                WrongAnswer();
            }
        });
        btn_quiz_choice3.setOnClickListener(view -> {
            // if correct
            if (btn_quiz_choice3.getText().toString().equals(questions.get(questionNumber).correct_answer)) {
                Toast.makeText(QuizLevelThree.this, "Correct!", Toast.LENGTH_SHORT).show();
                questionNumber++;
                NextQuestion();
            } else {
                WrongAnswer();
            }
        });
    }

    // Prepare UI for the next question
    private void NextQuestion() {
        // if won
        if (questionNumber == 15) {
            Intent intent = new Intent(QuizLevelThree.this, QuizComplete.class);
            intent.putExtra("reward_points", reward_points);
            intent.putExtra("total_questions", 15);
            intent.putExtra("wrong_answers_count", wrong_answers_count);
            startActivity(intent);
            finish();
            return;
        }
        tv_quiz_number.setText("Question " + (questionNumber + 1) + " of 15");
        tv_quiz_question.setText(questions.get(questionNumber).question);
        btn_quiz_choice1.setText(questions.get(questionNumber).option1);
        btn_quiz_choice2.setText(questions.get(questionNumber).option2);
        btn_quiz_choice3.setText(questions.get(questionNumber).option3);
    }

    private void WrongAnswer() {
        questionNumber++;
        reward_points -= 10;
        tv_points.setText("" + reward_points);
        Toast.makeText(QuizLevelThree.this, "Wrong. Minus 10 points.", Toast.LENGTH_SHORT).show();
        wrong_answers_count++;
        NextQuestion();
    }
}