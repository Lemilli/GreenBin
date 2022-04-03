package com.example.misterbin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class RecentTransaction2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_transaction2);

        ImageView backButton = findViewById(R.id.recent_transaction_page2_back_button);
        CardView transferButton = findViewById(R.id.recent_transaction_transfer_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RecentTransaction2.this, SendMoneyPage.class);
                startActivity(i);
                finish();
            }
        });

        transferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RecentTransaction2.this, TransactionSuccessfulPage.class);
                startActivity(i);

            }
        });
    }
}