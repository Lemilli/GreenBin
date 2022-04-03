package com.example.misterbin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class WalletPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_page);

        CardView voucherButton = findViewById(R.id.wallet_voucher_button);

        voucherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WalletPage.this, PointPage.class);
                startActivity(i);
            }
        });

        CardView payButton = findViewById(R.id.wallet_pay_button);

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WalletPage.this,PayPage.class);
                startActivity(i);
            }
        });

        ImageView profileButton = findViewById(R.id.wallet_profile_button);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WalletPage.this,Profile.class);
                startActivity(i);
                finish();
            }
        });

        CardView sendButton = findViewById(R.id.wallet_send_button);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WalletPage.this,SendMoneyPage.class);
                startActivity(i);
            }
        });
    }
}