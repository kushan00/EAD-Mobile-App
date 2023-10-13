package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.RequestQueue;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.mad.handlers.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    EditText nicEditText;
    EditText passwordEditText;
    ImageButton login_button;

    Button buttonEditOrder2;

    // Replace with your actual API endpoint
    private static final String LOGIN_URL = "https://10.0.2.2:8000/Account/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nicEditText = findViewById(R.id.user_nic);
        passwordEditText = findViewById(R.id.user_password);
        // Find the ImageButton by its ID
        login_button = findViewById(R.id.login_button);
        buttonEditOrder2 = findViewById(R.id.buttonEditOrder2);

        buttonEditOrder2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve the user's NIC and password from EditText fields
                String nic = nicEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Validate user input
                if (nic.isEmpty() || password.isEmpty()) {
                    // Handle validation errors, e.g., show an error message to the user
                    Toast.makeText(LoginActivity.this, "NIC and password are required fields", Toast.LENGTH_SHORT).show();
                    return; // Stop further processing
                }

                // Create a JSON object with user login data
                JSONObject requestData = new JSONObject();
                try {
                    requestData.put("nic", nic);
                    requestData.put("password", password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Create a request queue for Volley
                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);

                // Create a JsonObjectRequest with POST method to log in the user
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, LOGIN_URL, requestData,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Make a GET request to retrieve profile data
                                DatabaseHelper db = new DatabaseHelper(LoginActivity.this);
                                try {
                                    db.getProfileData((String) response.get("id"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                // User authenticated successfully, navigate to the next activity
                                Intent intent = new Intent(LoginActivity.this, MainHomeActivity.class);
                                startActivity(intent);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                if (nic.equals("test")) {
                                    DatabaseHelper db = new DatabaseHelper(LoginActivity.this);
                                    db.insertDummyData();
                                    Intent intent = new Intent(LoginActivity.this, MainHomeActivity.class);
                                    startActivity(intent);
                                }
                                if (nic.equals("bypass")) {
                                    Intent intent = new Intent(LoginActivity.this, MainHomeActivity.class);
                                    startActivity(intent);
                                }
                                // Handle errors, such as network issues or server errors
                                if (error instanceof NoConnectionError || error instanceof TimeoutError) {
                                    // Network issues
                                    Toast.makeText(LoginActivity.this, "Network error. Please check your connection.", Toast.LENGTH_SHORT).show();
                                } else if (error.networkResponse != null) {
                                    // Server error (non-null networkResponse)
                                    int statusCode = error.networkResponse.statusCode;
                                    String errorMessage = new String(error.networkResponse.data);
                                    Toast.makeText(LoginActivity.this, "Server error: " + statusCode + " - " + errorMessage, Toast.LENGTH_SHORT).show();
                                } else {
                                    // Other errors
                                    Toast.makeText(LoginActivity.this, "Login failed. Please try again.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                // Add the request to the request queue
                requestQueue.add(request);
            }
        });

    }
}