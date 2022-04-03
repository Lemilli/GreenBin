package com.example.misterbin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GetHelpPage extends AppCompatActivity {

    private TextView getHelpMap;
    private TextView getHelpWallet;
    private TextView getHelpVoucher;

    private ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_help_page);

        getHelpMap = findViewById(R.id.help_map_button);
        getHelpWallet = findViewById(R.id.help_wallet_button);
        getHelpVoucher = findViewById(R.id.help_voucher_button);

        backArrow = findViewById(R.id.GetHelpPage_backArrow);

        getHelpMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(GetHelpPage.this, HelpMap.class);
                startActivity(i);
                finish();
            }
        });

        getHelpWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(GetHelpPage.this, HelpWallet.class);
                startActivity(i);
                finish();
            }
        });

        getHelpVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(GetHelpPage.this, HelpVoucher.class);
                startActivity(i);
                finish();
            }
        });

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GetHelpPage.this, Profile.class);
                startActivity(i);
                finish();
            }
        });
    }
}