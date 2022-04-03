package com.example.misterbin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.misterbin.adapter.ArticlesTopPicksAdapter;
import com.example.misterbin.adapter.VideosEditorsPickAdapter;
import com.example.misterbin.model.ArticlesTopPicksData;
import com.example.misterbin.adapter.ArticlesDiscoverAdapter;
import com.example.misterbin.model.ArticlesDiscoverData;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ArticlesMain extends AppCompatActivity {

    private TextView articlesListButton1;
    private TextView articlesListButton2;
    private Button articleSixButton;
    private ImageButton backButton;

    //This is for the horizontal recycler view scroll
    RecyclerView articlesTopPicksRecycler;
    ArticlesTopPicksAdapter articlesTopPicksAdapter;
    RecyclerView articlesDiscoverRecycler;
    ArticlesDiscoverAdapter articlesDiscoverAdapter;

    private ArticlesTopPicksAdapter.RecyclerViewClickListener listener;
    List<ArticlesTopPicksData> articlesTopPicksDataList = new ArrayList<>();
    private ArticlesDiscoverAdapter.RecyclerViewClickListener listener2;
    List<ArticlesDiscoverData> articlesDiscoverDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles_main);

        //Data for the Top Picks Article recycler view scroll

        articlesTopPicksDataList.add(new ArticlesTopPicksData("What is Recycling?", "Fermin Koop", R.drawable.article_one_cover, 1));
        articlesTopPicksDataList.add(new ArticlesTopPicksData("One Minute Recycling", "Sebastian De Cabo", R.drawable.article_two_cover, 2));
        articlesTopPicksDataList.add(new ArticlesTopPicksData("The Steps of Recycling", "Fermin Koop", R.drawable.article_three_cover, 3));
        articlesTopPicksDataList.add(new ArticlesTopPicksData("Reduce, Reuse, Recycle", "Rougue Disposal", R.drawable.article_four_cover, 4));

        setArticlesTopPicksRecycler(articlesTopPicksDataList);

        articlesDiscoverDataList.add(new ArticlesDiscoverData("Recycled Materials", "Fermin Koop", R.drawable.article_five_cover, 1));
        articlesDiscoverDataList.add(new ArticlesDiscoverData("The Future of Recycling", "RTS Blog", R.drawable.article_six_cover, 2));
        articlesDiscoverDataList.add(new ArticlesDiscoverData("Recycling Trends", "Harmony Enterprises", R.drawable.article_eight_cover, 3));
        articlesDiscoverDataList.add(new ArticlesDiscoverData("Recycling Statistics", "Recycle Coach", R.drawable.article_nine_cover, 4));

        setArticlesDiscoverRecycler(articlesDiscoverDataList);

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

        articlesListButton1 = findViewById(R.id.videos_list_button1);
        articlesListButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticlesMain.this, ArticlesList.class);
                startActivity(intent);
                finish();
            }
        });

        articlesListButton2 = findViewById(R.id.articles_list_button2);
        articlesListButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticlesMain.this, ArticlesList.class);
                startActivity(intent);
                finish();
            }
        });

        articleSixButton = findViewById(R.id.article_six_button);
        articleSixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticlesMain.this, ArticleSix.class);
                startActivity(intent);
                finish();
            }
        });

        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticlesMain.this, Education.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //This is for the horizontal recycler view scroll
    private void setArticlesTopPicksRecycler(List<ArticlesTopPicksData> articlesTopPicksDataList){
        setOnClickListener();
        articlesTopPicksRecycler = findViewById(R.id.videoseditorspick_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        articlesTopPicksRecycler.setLayoutManager(layoutManager);
        articlesTopPicksAdapter = new ArticlesTopPicksAdapter(this, articlesTopPicksDataList, listener);
        articlesTopPicksRecycler.setAdapter(articlesTopPicksAdapter);
    }

    //This is for the horizontal recycler view scroll
    private void setArticlesDiscoverRecycler(List<ArticlesDiscoverData> articlesDiscoverDataList){
        setOnClickListener2();
        articlesDiscoverRecycler = findViewById(R.id.videosoriginals_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        articlesDiscoverRecycler.setLayoutManager(layoutManager);
        articlesDiscoverAdapter = new ArticlesDiscoverAdapter(this, articlesDiscoverDataList, listener2);
        articlesDiscoverRecycler.setAdapter(articlesDiscoverAdapter);
    }

    private void setOnClickListener() {
        listener = new ArticlesTopPicksAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Integer temp = articlesTopPicksDataList.get(position).getImagePosition();
                switch (temp)
                {
                    case 1:
                        Intent intent = new Intent(getApplicationContext(),ArticleOne.class);
                        intent.putExtra("article", articlesTopPicksDataList.get(position).getArticleName());
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getApplicationContext(),ArticleTwo.class);
                        intent.putExtra("article", articlesTopPicksDataList.get(position).getArticleName());
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(getApplicationContext(),ArticleThree.class);
                        intent.putExtra("article", articlesTopPicksDataList.get(position).getArticleName());
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(getApplicationContext(),ArticleFour.class);
                        intent.putExtra("article", articlesTopPicksDataList.get(position).getArticleName());
                        startActivity(intent);
                        break;
                }
            }
        };
    }

    private void setOnClickListener2() {
        listener2 = new ArticlesDiscoverAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Integer temp = articlesDiscoverDataList.get(position).getImagePosition();
                switch (temp)
                {
                    case 1:
                        Intent intent = new Intent(getApplicationContext(),ArticleFive.class);
                        intent.putExtra("article", articlesDiscoverDataList.get(position).getArticleName());
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getApplicationContext(),ArticleSix.class);
                        intent.putExtra("article", articlesDiscoverDataList.get(position).getArticleName());
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(getApplicationContext(),ArticleEight.class);
                        intent.putExtra("article", articlesDiscoverDataList.get(position).getArticleName());
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(getApplicationContext(),ArticleNine.class);
                        intent.putExtra("article", articlesDiscoverDataList.get(position).getArticleName());
                        startActivity(intent);
                        break;
                }
            }
        };
    }
}