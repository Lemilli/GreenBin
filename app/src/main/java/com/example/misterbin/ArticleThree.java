package com.example.misterbin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.misterbin.adapter.SuggestedArticlesAdapter;
import com.example.misterbin.model.SuggestedArticlesData;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ArticleThree extends AppCompatActivity {

    private TextView seeAllArticles;
    private ImageButton backButton;

    //This is for the horizontal recycler view scroll
    RecyclerView suggestedArticlesRecycler;
    SuggestedArticlesAdapter suggestedArticlesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_three);

        //Data for the Top Picks Article recycler view scroll
        List<SuggestedArticlesData> suggestedArticlesDataList = new ArrayList<>();
        suggestedArticlesDataList.add(new SuggestedArticlesData("One Minute Recycling", "Sebastian De Cabo", R.drawable.article_two_cover));
        suggestedArticlesDataList.add(new SuggestedArticlesData("Reduce, Reuse, Recycle", "Rougue Disposal", R.drawable.article_four_cover));
        suggestedArticlesDataList.add(new SuggestedArticlesData("The Future of Recycling (P1)", "RTS Blog", R.drawable.article_six_cover));
        suggestedArticlesDataList.add(new SuggestedArticlesData("The Five Recycling Trends", "Harmony Enterprises", R.drawable.article_eight_cover));

        setSuggestedArticlesRecycler(suggestedArticlesDataList);

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

        seeAllArticles = findViewById(R.id.see_all_articles_button);
        seeAllArticles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticleThree.this, ArticlesList.class);
                startActivity(intent);
                finish();
            }
        });

        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticleThree.this, ArticlesList.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //This is for the horizontal recycler view scroll
    private void setSuggestedArticlesRecycler(List<SuggestedArticlesData> suggestedArticlesDataList){
        suggestedArticlesRecycler = findViewById(R.id.suggestedarticles_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        suggestedArticlesRecycler.setLayoutManager(layoutManager);
        suggestedArticlesAdapter = new SuggestedArticlesAdapter(this, suggestedArticlesDataList);
        suggestedArticlesRecycler.setAdapter(suggestedArticlesAdapter);
    }

    public void onCustomToggleClick(View view) {
        Toast.makeText(this, "Favourite this Article.", Toast.LENGTH_SHORT).show();
    }
}