package com.example.misterbin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ArticlesList extends AppCompatActivity {

    private ConstraintLayout articleOneButton;
    private ConstraintLayout articleTwoButton;
    private ConstraintLayout articleThreeButton;
    private ConstraintLayout articleFourButton;
    private ConstraintLayout articleFiveButton;
    private ConstraintLayout articleSixButton;
    private ConstraintLayout articleSevenButton;
    private ConstraintLayout articleEightButton;
    private ConstraintLayout articleNineButton;
    private ConstraintLayout articleTenButton;
    private Button articleNineButtonPicked;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles_list);

        //Initialize and Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.nav_education);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
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

        articleOneButton = findViewById(R.id.articleone_button);
        articleOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticlesList.this, ArticleOne.class);
                startActivity(intent);
                finish();
            }
        });

        articleTwoButton = findViewById(R.id.articletwo_button);
        articleTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticlesList.this, ArticleTwo.class);
                startActivity(intent);
                finish();
            }
        });

        articleThreeButton = findViewById(R.id.articlethree_button);
        articleThreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticlesList.this, ArticleThree.class);
                startActivity(intent);
                finish();
            }
        });

        articleFourButton = findViewById(R.id.articlefour_button);
        articleFourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticlesList.this, ArticleFour.class);
                startActivity(intent);
                finish();
            }
        });

        articleFiveButton = findViewById(R.id.articlefive_button);
        articleFiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticlesList.this, ArticleFive.class);
                startActivity(intent);
                finish();
            }
        });

        articleSixButton = findViewById(R.id.articlesix_button);
        articleSixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticlesList.this, ArticleSix.class);
                startActivity(intent);
                finish();
            }
        });

        articleSevenButton = findViewById(R.id.articleseven_button);
        articleSevenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticlesList.this, ArticleSeven.class);
                startActivity(intent);
                finish();
            }
        });

        articleEightButton = findViewById(R.id.articleeight_button);
        articleEightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticlesList.this, ArticleEight.class);
                startActivity(intent);
                finish();
            }
        });

        articleNineButton = findViewById(R.id.articlenine_button);
        articleNineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticlesList.this, ArticleNine.class);
                startActivity(intent);
                finish();
            }
        });

        articleTenButton = findViewById(R.id.articleten_button);
        articleTenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticlesList.this, ArticleTen.class);
                startActivity(intent);
                finish();
            }
        });

        articleNineButtonPicked = findViewById(R.id.quiz_choice_one);
        articleNineButtonPicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticlesList.this, ArticleNine.class);
                startActivity(intent);
                finish();
            }
        });

        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticlesList.this, ArticlesMain.class);
                startActivity(intent);
                finish();
            }
        });
    }
}