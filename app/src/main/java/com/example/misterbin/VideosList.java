package com.example.misterbin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class VideosList extends AppCompatActivity {

    private ImageButton video1Button;
    private ImageButton video2Button;
    private ImageButton video3Button;
    private ImageButton video4Button;
    private ImageButton video5Button;
    private ImageButton video6Button;
    private ImageButton video7Button;
    private ImageButton video8Button;
    private ImageButton video9Button;
    private ImageButton video10Button;
    private Button pickedVideoButton;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_list);

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

        video1Button = findViewById(R.id.videoone_Button);
        video1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.youtube.com/watch?v=Iw4g6H7alvo");
            }
        });

        video2Button = findViewById(R.id.videotwo_Button);
        video2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.youtube.com/watch?v=OasbYWF4_S8");
            }
        });

        video3Button = findViewById(R.id.videothree_Button);
        video3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.youtube.com/watch?v=6jQ7y_qQYUA");
            }
        });

        video4Button = findViewById(R.id.videofour_Button);
        video4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.youtube.com/watch?v=L2Rc8oTOtd8");
            }
        });

        video5Button = findViewById(R.id.videofive_Button);
        video5Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.youtube.com/watch?v=STIs__mtBFk");
            }
        });

        video6Button = findViewById(R.id.videosix_Button);
        video6Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.youtube.com/watch?v=VlRVPum9cp4");
            }
        });

        video7Button = findViewById(R.id.videoseven_Button);
        video7Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.youtube.com/watch?v=J0eYST1qoLk");
            }
        });

        video8Button = findViewById(R.id.videoeight_Button);
        video8Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.youtube.com/watch?v=IXGr9qX5U8U");
            }
        });

        video9Button = findViewById(R.id.videonine_Button);
        video9Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.youtube.com/watch?v=tDEPBFv48UE");
            }
        });

        video10Button = findViewById(R.id.videoten_Button);
        video10Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.youtube.com/watch?v=R7N5a476DKQ");
            }
        });

        pickedVideoButton = findViewById(R.id.picked_video_button);
        pickedVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.youtube.com/watch?v=tDEPBFv48UE");
            }
        });

        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VideosList.this, VideosMain.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}