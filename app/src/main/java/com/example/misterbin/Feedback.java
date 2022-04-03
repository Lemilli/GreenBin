package com.example.misterbin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Feedback extends AppCompatActivity {

    //pop up window after clicking on the submit button
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        myDialog = new Dialog(this);
        //back to profile page
        ImageView backArrow = findViewById(R.id.Feedback_backArrow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Feedback.this, Profile.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void showPopUp(View v)
    {
        TextView feedbackTxt;
        ImageView checkIcon;
        Button returnButton;

        myDialog.setContentView(R.layout.feedback_pop_out_window);

        feedbackTxt = myDialog.findViewById(R.id.feedback_text);
        checkIcon = myDialog.findViewById(R.id.feedback_icon);
        returnButton = myDialog.findViewById(R.id.feedback_button);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
                Intent i = new Intent(Feedback.this, Profile.class);
                startActivity(i);
                finish();
            }
        });
        myDialog.show();
    }
}