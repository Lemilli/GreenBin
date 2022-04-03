package com.example.misterbin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class PointPage extends AppCompatActivity {

    Dialog myDialog;
    Dialog myDialog1;

    private ImageView back;
    private TextView tv_points;

    private CardView voucher1;
    private CardView voucher2;
    private CardView voucher3;
    private CardView voucher4;
    private CardView voucher5;
    private CardView voucher6;
    private CardView voucher7;
    private CardView voucher8;
    private CardView voucher9;
    private CardView voucher10;
    private CardView voucher11;
    private CardView voucher12;
    private CardView voucher13;
    private CardView voucher14;

    int pointAmount;
    String email;

    public String[] voucher_headings = {
            "Grab Ride",
            "Grab Ride",
            "Grab Ride",
            "Food Panda",
            "Food Panda",
            "KFC Ala Carte",
            "KFC Bucket",
            "KFC Set Meal",
            "Mc Donals's",
            "IKEA Furniture",
            "IKEA Furniture",
            "TGV Cinema",
            "GSC Cinema",
            "TGV Cinema"
    };

    public String[] voucher_descriptions = {
            "Claim 15% Offer",
            "Claim 25% Offer",
            "Claim 50% Offer",
            "Claim 10% Offer",
            "Claim 20% Offer",
            "Free Cheesy Wedges",
            "Claim RM10 Offer",
            "50% Offer Zinger",
            "Free 1 Set Meal",
            "Claim 22% Offer",
            "Claim 33% Offer",
            "Free 2 Deluxe",
            "Free 2 Tickets",
            "Free 2 Beanie"
    };

    public int[] voucher_costs = {
            20, 25, 45, 35, 55, 15, 30, 50, 35, 125, 210, 55, 125, 230
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_page);


        Bundle b = getIntent().getExtras();
        if (b != null) {
            pointAmount = b.getInt("points");
            email = b.getString("email");
        }

        myDialog = new Dialog(this);
        myDialog1 = new Dialog(this);

        back = findViewById(R.id.voucherPage_backButton);
        tv_points = findViewById(R.id.pointAmount);

        tv_points.setText("" + pointAmount);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PointPage.this, Profile.class);
                startActivity(i);
                finish();
            }
        });

        voucher1 = findViewById(R.id.voucher1);
        voucher2 = findViewById(R.id.voucher2);
        voucher3 = findViewById(R.id.voucher3);
        voucher4 = findViewById(R.id.voucher4);
        voucher5 = findViewById(R.id.voucher5);
        voucher6 = findViewById(R.id.voucher6);
        voucher7 = findViewById(R.id.voucher7);
        voucher8 = findViewById(R.id.voucher8);
        voucher9 = findViewById(R.id.voucher9);
        voucher10 = findViewById(R.id.voucher10);
        voucher11 = findViewById(R.id.voucher11);
        voucher12 = findViewById(R.id.voucher12);
        voucher13 = findViewById(R.id.voucher13);
        voucher14 = findViewById(R.id.voucher14);

        voucher1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp(view, 0, voucher1);
            }
        });

        voucher2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp(view, 1, voucher2);
            }
        });

        voucher3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp(view, 2, voucher3);
            }
        });

        voucher4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp(view, 3, voucher4);
            }
        });

        voucher5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp(view, 4, voucher5);
            }
        });

        voucher6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp(view, 5, voucher6);
            }
        });

        voucher7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp(view, 6, voucher7);
            }
        });

        voucher8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp(view, 7, voucher8);
            }
        });

        voucher9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp(view, 8, voucher9);
            }
        });

        voucher10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp(view, 9, voucher10);
            }
        });

        voucher11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp(view, 10, voucher11);
            }
        });

        voucher12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp(view, 11, voucher12);
            }
        });

        voucher13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp(view, 12, voucher13);
            }
        });

        voucher14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp(view, 13, voucher14);
            }
        });
    }

    //show the pop up window
    public void showPopUp(View v, int choices, CardView voucherSelected) {
        Button yesButton;
        Button noButton;
        TextView closeButton;

        TextView voucherHeadings;
        TextView voucherDesc;
        ConstraintLayout voucherBackground;

        int cost = voucher_costs[choices];

        myDialog.setContentView(R.layout.voucher_pop_up_window);
        myDialog1.setContentView(R.layout.promo_code_pop_up_window);

        yesButton = myDialog.findViewById(R.id.yesButton);
        noButton = myDialog.findViewById(R.id.noButton);
        closeButton = myDialog1.findViewById(R.id.voucher_close);

        voucherHeadings = myDialog1.findViewById(R.id.voucher_category);
        voucherDesc = myDialog1.findViewById(R.id.voucher_desc);
        voucherBackground = myDialog1.findViewById(R.id.voucher_bg);

        //change voucher headings
        voucherHeadings.setText(voucher_headings[choices]); //change text

        //change voucher description
        voucherDesc.setText(voucher_descriptions[choices]);

        //change background
        switch (choices) {
            case 0:
            case 1:
            case 2:
                voucherBackground.setBackground(getResources().getDrawable(R.drawable.voucher_bg_1));
                break;
            case 3:
            case 4:
                voucherBackground.setBackground(getResources().getDrawable(R.drawable.voucher_bg_2));
                break;
            case 5:
            case 6:
            case 7:
                voucherBackground.setBackground(getResources().getDrawable(R.drawable.voucher_bg_text_3));
                break;
            case 8:
                voucherBackground.setBackground(getResources().getDrawable(R.drawable.voucher_bg_text_4));
                break;
            case 9:
            case 10:
                voucherBackground.setBackground(getResources().getDrawable(R.drawable.voucher_bg_text_5));
                break;
            case 11:
                voucherBackground.setBackground(getResources().getDrawable(R.drawable.voucher_bg_6));
                break;
            case 12:
                voucherBackground.setBackground(getResources().getDrawable(R.drawable.voucher_bg_7));
                break;
            case 13:
                voucherBackground.setBackground(getResources().getDrawable(R.drawable.voucher_bg_8));
                break;


        }


        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss(); //close the dialog
                //show another pop up window

                if (pointAmount >= cost) {
                    // Notify server that we spent points
                    String URL = "https://mobiledevuniassign.herokuapp.com/add_points/";
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                    JSONObject postData = new JSONObject();
                    try {
                        postData.put("email", email);
                        postData.put("points", -cost);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("REST Response", "AAAAAAAAAA " + "error with JSON");
                    }

                    // Request a JSON response from the provided URL.
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                            URL,
                            postData,
                            response -> {
                                Log.e("REST Response", "AAAAAAAAAA " + response);
                                myDialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                myDialog1.show();

                                pointAmount = pointAmount - cost;
                                tv_points.setText(String.valueOf(pointAmount));

                                voucherSelected.setAlpha(Float.parseFloat("0.2")); //id to disable other voucher
                                voucherSelected.setOnClickListener(null);
                            },
                            error -> {
                                Log.e("REST Response", "AAAAAAAAAA " + error.toString());
                                Toast.makeText(getApplicationContext(), "Error. Server didn't accept the request.", Toast.LENGTH_LONG).show();
                            }
                    );

                    requestQueue.add(jsonObjectRequest);

                } else {
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, "Points not enough", duration);
                    toast.show();
                }
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss(); //close the dialog

            }
        });


        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog1.dismiss();
            }
        });


        myDialog.show();

    }

}