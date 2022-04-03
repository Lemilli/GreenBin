package com.example.misterbin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.misterbin.model.TerminalData;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private GoogleMap mMap;
    private ArrayList<TerminalData> terminals = new ArrayList<TerminalData>();

    private ImageView profileButton;

    // init widgets
    TextView tv_terminal_name, tv_fullness, tv_street;
    ImageView iv_terminal_img, availability_circle;
    LinearLayout ll_marker_details;
    Button btn_directions;
    AutoCompleteTextView auto_tv_search_terminal;

    private Button selectLocationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Set up values for widgets
        tv_terminal_name = findViewById(R.id.terminal_name);
        tv_fullness = findViewById(R.id.tv_fullness);
        ll_marker_details = findViewById(R.id.ll_marker_details);
        tv_street = findViewById(R.id.tv_street);
        iv_terminal_img = findViewById(R.id.iv_terminal_img);
        availability_circle = findViewById(R.id.availability_circle);
        btn_directions = findViewById(R.id.btn_directions);
        auto_tv_search_terminal = findViewById(R.id.auto_tv_search_terminal);

        //Initialize and Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

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

        profileButton = findViewById(R.id.imageView);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Profile.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * If Google Play services is not installed on the device, the user will xbe prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Request location permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);

            return;
        }

        // Enable My location button
        // on Top Right
        mMap.setMyLocationEnabled(true);
        //mMap.getUiSettings().setMyLocationButtonEnabled(true);


        // REST API connection
        String URL = "https://mobiledevuniassign.herokuapp.com/terminal/";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Request a JSON response from the provided URL.
        JsonArrayRequest objectRequest = new JsonArrayRequest(
                URL,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            double lat = jsonObject.getDouble("lat");
                            double lng = jsonObject.getDouble("lng");
                            String name = jsonObject.getString("name");
                            String street = jsonObject.getString("street");
                            int fullness = jsonObject.getInt("fullness");
                            boolean isAvailable = jsonObject.getBoolean("isAvailable");
                            String image_link = jsonObject.getString("image_link");

                            TerminalData terminal = new TerminalData(lat, lng, name, street, fullness, isAvailable, image_link);
                            terminals.add(terminal);
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Error loading terminals. Try again later.", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }

                    // Assign markers on the map
                    // I.e., show terminals
                    for (int i = 0; i < terminals.size(); i++) {
                        LatLng position = new LatLng(terminals.get(i).lat, terminals.get(i).lng);
                        mMap.addMarker(new MarkerOptions().position(position).title(terminals.get(i).name))
                                .setIcon(BitmapFromVector(getApplicationContext(), R.drawable.ic_terminal_marker));
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(terminals.get(1).lat, terminals.get(1).lng), 12.0f));

                    // Dropdown suggestions for search
                    ArrayList<String> terminalNames = new ArrayList<String>();
                    for (int i = 0; i < terminals.size(); i++) {
                        terminalNames.add(terminals.get(i).name);
                    }
                    auto_tv_search_terminal.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, terminalNames));
                    auto_tv_search_terminal.setOnItemClickListener((adapterView, view, i, l) -> {
                        // Hide keyboard
                        View keyboard_view = this.getCurrentFocus();
                        if (keyboard_view != null) {
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(keyboard_view.getWindowToken(), 0);
                        }

                        // Move camera on to the terminal according to the search query
                        int chosen_terminal_id = terminalNames.indexOf(auto_tv_search_terminal.getText().toString());
                        ll_marker_details.setVisibility(View.INVISIBLE);
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(terminals.get(chosen_terminal_id).lat, terminals.get(chosen_terminal_id).lng), 14.0f));
                        Log.e("REST Response", "AAAAAAAAAA  ID " + chosen_terminal_id);
                    });

                    Log.e("REST Response", "AAAAAAAAAA " + response.toString());
                },
                error -> {
                    Toast.makeText(getApplicationContext(), "Error loading terminals. Try again later.", Toast.LENGTH_LONG).show();
                }
        );

        requestQueue.add(objectRequest);

        // called every time any marker is clicked
        mMap.setOnMarkerClickListener(marker -> {

            // Find specific terminal that user selected
            TerminalData selected_terminal = null;
            for (int i = 0; i < terminals.size(); i++) {
                if (marker.getTitle().equals(terminals.get(i).name)) {
                    selected_terminal = terminals.get(i);
                }
            }
            // In case there's a bug that doesn't find selected terminal
            // make the first terminal as selected one
            // It will probably never happen, but it's better to make app
            // safe against crashes
            if (selected_terminal == null) {
                selected_terminal = terminals.get(0);
            }

            // handle loading image from the internet and assigning it to the image view
            // Uses Picasso package
            Picasso.get().load(selected_terminal.image_url).into(iv_terminal_img);

            // Show marker details
            tv_terminal_name.setText(selected_terminal.name);
            tv_fullness.setText(selected_terminal.fullness + "%");
            tv_street.setText(selected_terminal.street);

            if (selected_terminal.isAvailable) {
                availability_circle.setColorFilter(Color.parseColor("#80D215"));
            } else {
                availability_circle.setColorFilter(Color.parseColor("#e61220"));
            }

            // Open Google maps on click
            final double lat = selected_terminal.lat;
            final double lng = selected_terminal.lng;
            btn_directions.setOnClickListener(view -> {
                Uri.Builder builder = new Uri.Builder();
                builder.scheme("https")
                        .authority("www.google.com")
                        .appendPath("maps")
                        .appendPath("dir")
                        .appendPath("")
                        .appendQueryParameter("api", "1")
                        .appendQueryParameter("destination", lat + "," + lng);
                String url = builder.build().toString();
                Log.d("Directions", url);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            });

            // make details visible
            ll_marker_details.setVisibility(View.VISIBLE);

            return true;
        });

        // Hide terminal details if click on map
        mMap.setOnMapClickListener(latLng -> {
            ll_marker_details.setVisibility(View.INVISIBLE);
        });
    }

    // Convert vector to a bitmap
    private BitmapDescriptor BitmapFromVector(Context context, int vectorResId) {
        // below line is use to generate a drawable.
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);

        // below line is use to set bounds to our vector drawable.
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());

        // below line is use to create a bitmap for our
        // drawable which we have added.
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        // below line is use to add bitmap in our canvas.
        Canvas canvas = new Canvas(bitmap);

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas);

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}