package com.example.misterbin;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupTabFragment extends Fragment {

    EditText email, username, pass, confirmpass;
    Button signup;
    float v = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);

        email = root.findViewById(R.id.email);
        username = root.findViewById(R.id.username);
        pass = root.findViewById(R.id.pass);
        confirmpass = root.findViewById(R.id.confirmpass);
        signup = root.findViewById(R.id.btn_signup);

        email.setTranslationX(800);
        username.setTranslationX(800);
        pass.setTranslationX(800);
        confirmpass.setTranslationX(800);
        signup.setTranslationX(800);

        email.setAlpha(v);
        username.setAlpha(v);
        pass.setAlpha(v);
        confirmpass.setAlpha(v);
        signup.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        username.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        confirmpass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        signup.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();


        signup.setOnClickListener(view -> {
            String usernameText = username.getText().toString();
            String emailText = email.getText().toString();
            String passText = pass.getText().toString();
            String passConfirmText = confirmpass.getText().toString();

            if (usernameText.length() == 0 || emailText.length() == 0 || passText.length() == 0 || passConfirmText.length() == 0) {
                Toast.makeText(getActivity().getApplicationContext(), "All fields should not be empty.", Toast.LENGTH_LONG).show();
                return;
            } else if (!usernameText.matches("^[a-zA-Z0-9]+([_ -]?[a-zA-Z0-9])*$")) {
                Toast.makeText(getActivity().getApplicationContext(), "The username is invalid.", Toast.LENGTH_LONG).show();
                return;
            } else if (!emailText.matches("^\\S+@\\S+\\.\\S+$")){
                // Regex filters:
                // bob @ aol.com (spaces in emails)
                // steve (no domain at all)
                // mary@aolcom (no period before .com)
                Toast.makeText(getActivity().getApplicationContext(), "The email is invalid.", Toast.LENGTH_LONG).show();
                return;
            } else if(!passText.matches("^[a-zA-Z0-9]+([_ -]?[a-zA-Z0-9])*$")){
                Toast.makeText(getActivity().getApplicationContext(), "The password is invalid.", Toast.LENGTH_LONG).show();
                return;
            } else if(passText.length() < 8){
                Toast.makeText(getActivity().getApplicationContext(), "The password should have at least 8 characters.", Toast.LENGTH_LONG).show();
                return;
            } else if(!passText.equals(passConfirmText)){
                Toast.makeText(getActivity().getApplicationContext(), "Passwords don't match.", Toast.LENGTH_LONG).show();
                return;
            }

            // REST API connection
            String URL = "https://mobiledevuniassign.herokuapp.com/register/";
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

            JSONObject postData = new JSONObject();
            try {
                postData.put("username", usernameText);
                postData.put("email", emailText);
                postData.put("password", passText);

            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("REST Response", "AAAAAAAAAA " + "Invalid post data");
            }

            // Request a JSON response from the provided URL.
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    URL,
                    postData,
                    response -> {
                        Log.e("REST Response", "AAAAAAAAAA " + response);

                        // Save username into shared prefs
                        // Persistent storage basically
                        SharedPreferences.Editor editor = getActivity().getSharedPreferences("PrefsFile", MODE_PRIVATE).edit();
                        editor.putString("email", emailText);
                        editor.apply();

                        Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    },
                    error -> {
                        Log.e("REST Response", "AAAAAAAAAA " + error.toString());
                        Toast.makeText(getActivity().getApplicationContext(), "Error. User already exists.", Toast.LENGTH_LONG).show();
                    }
            );

            requestQueue.add(jsonObjectRequest);

        });

        return root;
    }
}
