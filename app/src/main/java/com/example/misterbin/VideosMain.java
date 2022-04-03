package com.example.misterbin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.misterbin.adapter.VideosEditorsPickAdapter;
import com.example.misterbin.model.VideosEditorsPickData;
import com.example.misterbin.adapter.VideosOriginalsAdapter;
import com.example.misterbin.model.VideosOriginalsData;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class VideosMain extends AppCompatActivity {

    private TextView videosListButton1;
    private TextView videosListButton2;
    private Button video10Button;
    private ImageButton backButton;

    //This is for the horizontal recycler view scroll
    RecyclerView videosEditorsPickRecycler;
    VideosEditorsPickAdapter videosEditorsPickAdapter;
    RecyclerView videosOriginalsRecycler;
    VideosOriginalsAdapter videosOriginalsAdapter;

    private VideosEditorsPickAdapter.RecyclerViewClickListener listener;
    List<VideosEditorsPickData> videosEditorsPickDataList = new ArrayList<>();

    private VideosOriginalsAdapter.RecyclerViewClickListener listener2;
    List<VideosOriginalsData> videosOriginalsDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_main);

        //Data for the Top Picks Article recycler view scroll
        videosEditorsPickDataList.add(new VideosEditorsPickData("How Computers are Recycled?", "Tune in to find out technologies that offer unsurpassed recycling capabilities.", R.drawable.video_one_cover,1));
        videosEditorsPickDataList.add(new VideosEditorsPickData("Reduce, Reuse and Recycle", "The 3R's are the magical words that can save the world. Watch the video to find out how!", R.drawable.video_two_cover,2));
        videosEditorsPickDataList.add(new VideosEditorsPickData("Recycling Plastic, Glass and Paper", "Recycling is a process that involves improving disposable things. Learn more!", R.drawable.video_three_cover,3));
        videosEditorsPickDataList.add(new VideosEditorsPickData("US Largest Recycling Facility", "The Sims Municipal Recycling Facility in Brooklyn is the largest recycling facility in the country.", R.drawable.video_four_cover,4));

        setVideosEditorsPickRecycler(videosEditorsPickDataList);

        videosOriginalsDataList.add(new VideosOriginalsData("Treasures from Trash", "The world produces approximately 50 million tons of electronic waste each year.", R.drawable.video_five_cover, 1));
        videosOriginalsDataList.add(new VideosOriginalsData("How Recycling Works?", "Humans make a lot of garbage every day, and a lot of it ends up in big, smelly dumps. ", R.drawable.video_six_cover, 2));
        videosOriginalsDataList.add(new VideosOriginalsData("The Future of Recycling", "Stena is investing heavily in the future of recycling technology. Watch the video to know more!", R.drawable.video_seven_cover, 3));
        videosOriginalsDataList.add(new VideosOriginalsData("3 Recycling Trends", "THE LIST is a daily TV show that covers the hottest trends in recycling.", R.drawable.video_eight_cover, 4));

        setVideosOriginalsRecycler(videosOriginalsDataList);

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

        videosListButton1 = findViewById(R.id.videos_list_button1);
        videosListButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VideosMain.this, VideosList.class);
                startActivity(intent);
                finish();
            }
        });

        videosListButton2 = findViewById(R.id.videos_list_button2);
        videosListButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VideosMain.this, VideosList.class);
                startActivity(intent);
                finish();
            }
        });

        video10Button = findViewById(R.id.featured_video_button);
        video10Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.youtube.com/watch?v=R7N5a476DKQ");
            }
        });

        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VideosMain.this, Education.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    //This is for the horizontal recycler view scroll
    private void setVideosEditorsPickRecycler(List<VideosEditorsPickData> videosEditorsPickDataList){

        setOnClickListener();
        videosEditorsPickRecycler = findViewById(R.id.videoseditorspick_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        videosEditorsPickRecycler.setLayoutManager(layoutManager);
        videosEditorsPickAdapter = new VideosEditorsPickAdapter(this, videosEditorsPickDataList,listener);
        videosEditorsPickRecycler.setAdapter(videosEditorsPickAdapter);
    }

    private void setOnClickListener() {
        listener = new VideosEditorsPickAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Integer temp = videosEditorsPickDataList.get(position).getImagePosition();
                switch (temp)
                {
                    case 1:
                        gotoUrl("https://www.youtube.com/watch?v=Iw4g6H7alvo");
                        break;
                    case 2:
                        gotoUrl("https://www.youtube.com/watch?v=OasbYWF4_S8");
                        break;
                    case 3:
                        gotoUrl("https://www.youtube.com/watch?v=6jQ7y_qQYUA");
                        break;
                    case 4:
                        gotoUrl("https://www.youtube.com/watch?v=L2Rc8oTOtd8");
                        break;
                }
            }
        };
    }

    //This is for the horizontal recycler view scroll
    private void setVideosOriginalsRecycler(List<VideosOriginalsData> videosOriginalsDataList){
        setOnClickListener2();
        videosOriginalsRecycler = findViewById(R.id.videosoriginals_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        videosOriginalsRecycler.setLayoutManager(layoutManager);
        videosOriginalsAdapter = new VideosOriginalsAdapter(this, videosOriginalsDataList, listener2);
        videosOriginalsRecycler.setAdapter(videosOriginalsAdapter);
    }

    private void setOnClickListener2() {
        listener2 = new VideosOriginalsAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Integer temp = videosOriginalsDataList.get(position).getImagePosition();
                switch (temp)
                {
                    case 1:
                        gotoUrl("https://www.youtube.com/watch?v=STIs__mtBFk");
                        break;
                    case 2:
                        gotoUrl("https://www.youtube.com/watch?v=VlRVPum9cp4");
                        break;
                    case 3:
                        gotoUrl("https://www.youtube.com/watch?v=J0eYST1qoLk");
                        break;
                    case 4:
                        gotoUrl("https://www.youtube.com/watch?v=IXGr9qX5U8U");
                        break;
                }

            }
        };
    }
}