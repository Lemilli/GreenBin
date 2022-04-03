package com.example.misterbin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class EditPersonalInformationPage extends AppCompatActivity {
    EditText et_first_name, et_last_name, et_gender, et_birth_date, et_phone_number;
    TextView tv_save;
    String email = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal_information_page);

        // Assign IDs
        et_first_name = findViewById(R.id.editProfile_FirstNameContent);
        et_last_name = findViewById(R.id.editProfile_LastNameContent);
        et_gender = findViewById(R.id.editProfile_GenderContent);
        et_birth_date = findViewById(R.id.editProfile_BirthDateContent);
        et_phone_number = findViewById(R.id.editProfile_PhoneNumberContent);

        tv_save = findViewById(R.id.editProfile_save);

        //back to profile page
        ImageView backArrow = findViewById(R.id.editProfile_backArrow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EditPersonalInformationPage.this, Profile.class);
                startActivity(i);
                finish();
            }
        });


        SharedPreferences prefs = getSharedPreferences("PrefsFile", MODE_PRIVATE);
        email = prefs.getString("email", null);

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
                        // Nullable fields
                        // if we try to pass directly then error might occur if value is null
                        String first_name = response.isNull("first_name") ? null : response.getString("first_name");
                        String last_name = response.isNull("last_name") ? null : response.getString("last_name");
                        String phone_number = response.isNull("phone_number") ? null : response.getString("phone_number");
                        String gender = response.isNull("gender") ? null : response.getString("gender");

                        String birth_date_raw = response.isNull("birth_date") ? null : response.getString("birth_date");
                        Calendar cal = null;
                        if (birth_date_raw != null) {
                            cal = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
                            cal.setTime(sdf.parse(birth_date_raw.replace("Z", "").replace("T", "-")));
                        }


                        // Show fetched data in the UI
                        if (first_name != null)
                            et_first_name.setText(first_name);

                        if (last_name != null)
                            et_last_name.setText(last_name);

                        if (cal != null) {
                            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Log.e("REST Response", "AAAAAAAAAA STR DATE: " + sdf.format(cal.getTime()));
                            et_birth_date.setText(sdf.format(cal.getTime()));
                        }

                        if (gender != null)
                            et_gender.setText(gender);

                        if (phone_number != null)
                            et_phone_number.setText(phone_number);
                    } catch (ParseException | JSONException e) {
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


        // Set click listener
        tv_save.setOnClickListener(view -> {

            // REST API connection
            String newURL = "https://mobiledevuniassign.herokuapp.com/update_user_data/" + email + "/";
            RequestQueue newRequestQueue = Volley.newRequestQueue(getApplicationContext());

            JSONObject postData = new JSONObject();
            try {
                postData.put("first_name", et_first_name.getText().toString());
                postData.put("last_name", et_last_name.getText().toString());
                postData.put("phone_number", et_phone_number.getText().toString());
                postData.put("birth_date", et_birth_date.getText().toString() + "T00:00");
                postData.put("gender", et_gender.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("REST Response", "AAAAAAAAAA " + "Invalid post data");
            }

            // Request a JSON response from the provided URL.
            JsonObjectRequest jsonObjectRequestSave = new JsonObjectRequest(Request.Method.PUT,
                    newURL,
                    postData,
                    response -> {
                        Log.e("REST Response", "AAAAAAAAAA " + response);
                        Toast.makeText(getApplicationContext(), "Successfully saved the data.", Toast.LENGTH_LONG).show();
                    },
                    error -> {
                        Log.e("REST Response", "AAAAAAAAAA " + error.toString());
                        Toast.makeText(getApplicationContext(), "Error. Format date as: YYYY-MM-DD.", Toast.LENGTH_LONG).show();
                    }
            );

            newRequestQueue.add(jsonObjectRequestSave);
        });
    }
}