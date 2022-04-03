package com.example.misterbin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SendMoneyPage extends AppCompatActivity {

    EditText accountNumber, accountName, transferAmount;
    int amount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money_page);

        ImageView send_money_back_button = findViewById(R.id.send_money_page_back_button);

        send_money_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SendMoneyPage.this, WalletPage.class);
                startActivity(i);
                finish();
            }
        });

        //user click on transfer button
        CardView transfer = findViewById(R.id.transfer_button);
        accountNumber = findViewById(R.id.editTextBeneficiaryAccount);
        accountName = findViewById(R.id.editTextBeneficiaryName);
        transferAmount  = findViewById(R.id.editTextTextTransferAmount);


        transfer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String accNumber = accountNumber.getText().toString();
                String accName = accountName.getText().toString();
                String transAmount = transferAmount.getText().toString();


                if(accName.length()==0 || accNumber.length() ==0 ||transAmount.length()==0)
                {
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, "Please enter the required information", duration);
                    toast.show();
                }
                else
                {
                    amount = Integer.parseInt(transAmount);

                    //check if the amount entered is larger than the balance
                    if(amount >25) //it should be wallet balance
                    {
                        Context context = getApplicationContext();
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, "Invalid Amount", duration);
                        toast.show();
                    }
                    else
                    {
                        Intent i = new Intent(SendMoneyPage.this, TransactionSuccessfulPage.class);
                        startActivity(i);
                        finish();
                    }

                }

            }
        });

        ImageView friend1 = findViewById(R.id.send_money_page_profile_1);
        ImageView friend2 = findViewById(R.id.send_money_page_profile_2);
        ImageView friend3 = findViewById(R.id.send_money_page_profile_3);

        friend1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SendMoneyPage.this, RecentTransaction1.class);
                startActivity(i);
                finish();
            }
        });

        friend2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SendMoneyPage.this, RecentTransaction2.class);
                startActivity(i);
                finish();
            }
        });

        friend3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SendMoneyPage.this, RecentTransaction3.class);
                startActivity(i);
                finish();
            }
        });
    }
}