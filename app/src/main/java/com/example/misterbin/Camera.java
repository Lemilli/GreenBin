package com.example.misterbin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class Camera extends AppCompatActivity {
    //Initialize variable
    Button btScan;
    Button btSubmitCode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        //Assign variable
        btScan = findViewById(R.id.bt_scan);
        btSubmitCode = findViewById(R.id.btn_ManualCode);

        btSubmitCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitySuccessfulPoint();
            }
        });

        btScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialize intent
                IntentIntegrator intentIntegrator = new IntentIntegrator(Camera.this);

                //set prompt text
                intentIntegrator.setPrompt("For flash use volume up key");

                //set beep
                intentIntegrator.setBeepEnabled(true);

                //locked orientation
                intentIntegrator.setOrientationLocked(true);

                //set capture activity
                intentIntegrator.setCaptureActivity(Capture.class);

                //initiate scan
                intentIntegrator.initiateScan();
            }
        });

        //Initialize and Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.nav_camera);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_camera:
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //initiate intent result
        IntentResult intentResult = IntentIntegrator.parseActivityResult(
                requestCode, resultCode, data
        );

        //check condition
        if (intentResult.getContents() != null) {
            openActivitySuccessfulPoint();
//            //When result content is not null
//            //Initiate alert dialog
//            AlertDialog.Builder builder = new AlertDialog.Builder(Camera.this);
//
//            //Set title
//            builder.setTitle("Result");
//
//            //Set message
//            builder.setMessage(intentResult.getContents());
//
//            //Set positive button
//            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    //Dismiss dialog
//                    dialogInterface.dismiss();
//
//                }
//            });
//
//            //Show alert dialog
//            builder.show();
        } else {
            //When result content is null
            //Display toast
            Toast.makeText(getApplicationContext(), "OOPS...try again!", Toast.LENGTH_SHORT).show();
        }
    }

    public void openActivitySuccessfulPoint() {
        Intent intent = new Intent(getApplicationContext(), SuccessfulPointsEarned.class);
        startActivity(intent);
    }
}