package com.example.misterbin;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginTabFragment extends Fragment {

    EditText email, pass;
    //    TextView forgetPass;
    Button login;
    TextView register;
    float v = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        email = root.findViewById(R.id.email);
        pass = root.findViewById(R.id.pass);
        //forgetPass = root.findViewById(R.id.forget_pass);
        login = root.findViewById(R.id.btn_login);
        register = root.findViewById(R.id.newUserText);

        email.setTranslationX(800);
        pass.setTranslationX(800);
//        forgetPass.setTranslationX(800);
        login.setTranslationX(800);
        register.setTranslationX(800);

        email.setAlpha(v);
        pass.setAlpha(v);
//        forgetPass.setAlpha(v);
        login.setAlpha(v);
        register.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
//        forgetPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        register.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();


        login.setOnClickListener(view -> {
            String emailText = email.getText().toString();
            String passText = pass.getText().toString();

            if (emailText.length() == 0 || passText.length() == 0) {
                Toast.makeText(getActivity().getApplicationContext(), "All fields should not be empty.", Toast.LENGTH_LONG).show();
                return;
            } else if (!emailText.matches("^\\S+@\\S+\\.\\S+$")) {
                // Regex filters:
                // bob @ aol.com (spaces in emails)
                // steve (no domain at all)
                // mary@aolcom (no period before .com)
                Toast.makeText(getActivity().getApplicationContext(), "The email is invalid.", Toast.LENGTH_LONG).show();
                return;
            } else if (!passText.matches("^[a-zA-Z0-9]+([_ -]?[a-zA-Z0-9])*$")) {
                Toast.makeText(getActivity().getApplicationContext(), "The password is invalid.", Toast.LENGTH_LONG).show();
                return;
            } else if (passText.length() < 8) {
                Toast.makeText(getActivity().getApplicationContext(), "The password should have at least 8 characters.", Toast.LENGTH_LONG).show();
                return;
            }

            // REST API connection
            String URL = "https://mobiledevuniassign.herokuapp.com/login/";
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

            JSONObject postData = new JSONObject();
            try {
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
                        Toast.makeText(getActivity().getApplicationContext(), "Error. Please, provide valid credentials.", Toast.LENGTH_LONG).show();
                    }
            );

            requestQueue.add(jsonObjectRequest);

        });

        return root;
    }
}
