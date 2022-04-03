package com.example.misterbin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SuccessfulPointsEarned extends AppCompatActivity {
    Button btn_check_balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_points_earned);

        btn_check_balance = findViewById(R.id.btn_checkbalance);

        btn_check_balance.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Profile.class);
            startActivity(intent);
            finish();
        });
    }
}