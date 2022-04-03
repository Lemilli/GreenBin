package com.example.misterbin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;

import org.json.JSONException;
import org.json.JSONObject;

public class Profile extends AppCompatActivity {
    TextView point_count, tv_email, tv_username;
    int points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Asign IDs
        point_count = findViewById(R.id.pointCount);
        tv_email = findViewById(R.id.email_profile);
        tv_username = findViewById(R.id.username_profile);

        //Initialize and Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.nav_profile);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
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
                        return true;
                }
                return false;
            }
        });


        SharedPreferences prefs = getSharedPreferences("PrefsFile", MODE_PRIVATE);
        String email = prefs.getString("email", null);

        // REST API connection
        String URL = "https://mobiledevuniassign.herokuapp.com/user_data/" + email + "/";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        // Request a JSON response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                URL,
                null,
                response -> {
                    try {
                        Log.e("REST Response", "AAAAAAAAAA " + response);
                        // Using optString doesn't throw an error if encounters null value
                        // it simply assigns null directly to the variable
//                        String phone_number = response.optString("phone_number");
//                        String birth_date = response.optString("datetime");
//                        String gender = response.optString("gender");
                        points = response.getInt("points");
                        JSONObject user_details = response.optJSONObject("user");
                        String username = user_details.getString("username");

                        // Show fetched data in the UI
                        tv_username.setText(username);
                        point_count.setText("" + points);
                        if (email != null)
                            tv_email.setText(email);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Failed to load details. Try again later.", Toast.LENGTH_LONG).show();
                    }
                },
                error -> {
                    Log.e("REST Response", "AAAAAAAAAA " + error.toString());
                    Toast.makeText(getApplicationContext(), "Failed to load details. Try again later.", Toast.LENGTH_LONG).show();
                }
        );

        requestQueue.add(jsonObjectRequest);

        //link to wallet page
        MaterialCardView walletButton = findViewById(R.id.walletCard);

        walletButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profile.this, WalletPage.class);
                startActivity(i);
            }
        });

        //link to point page
        MaterialCardView pointButton = findViewById(R.id.pointCard);

        pointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profile.this, PointPage.class);
                Bundle b = new Bundle();
                b.putInt("points", points);
                b.putString("email", email);
                i.putExtras(b); //Put your id to your next Intent
                startActivity(i);
            }
        });

        //link to edit information page
        TextView personalInfo = findViewById(R.id.PersonalInfo);

        personalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profile.this, EditPersonalInformationPage.class);
                startActivity(i);
            }
        });

        //link to settings page
        TextView settings = findViewById(R.id.Settings);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profile.this, Settings.class);
                startActivity(i);
            }
        });

        //link to terms of services page
        TextView terms = findViewById(R.id.termsOfService);

        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profile.this, TermsOfService.class);
                startActivity(i);
                finish();
            }
        });

        //link to get help page
        TextView getHelp = findViewById(R.id.getHelp);

        getHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profile.this, GetHelpPage.class);
                startActivity(i);
            }
        });

        //link to feedback page
        TextView feedback = findViewById(R.id.feedback);

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profile.this, Feedback.class);
                startActivity(i);
            }
        });

        TextView logout = findViewById(R.id.logout);

        logout.setOnClickListener(view -> {
            Intent i = new Intent(Profile.this, loginpage.class);
            startActivity(i);
        });

    }
}