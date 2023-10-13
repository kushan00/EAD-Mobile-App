package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mad.handlers.DatabaseHelper;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    EditText customerName;
    EditText user_nic;
    EditText customerpassword;
    EditText user_number;
    EditText customerNic;
    EditText customerdob;
    EditText customerAddress;
    EditText customerGender;
    ImageButton register_button;
    ImageButton back_button;

    private static final String REGISTER_URL = "https://10.0.2.2:8000/Account";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        customerName = findViewById(R.id.customerName1);
        user_nic = findViewById(R.id.user_nic);
        customerpassword = findViewById(R.id.customerpassword);
        user_number = findViewById(R.id.user_number);
        customerNic = findViewById(R.id.customerNic);
        customerdob = findViewById(R.id.customerdob);
        customerAddress = findViewById(R.id.customerAddress);
        customerGender = findViewById(R.id.customerGender);

        // Find the Button by its ID
        register_button = findViewById(R.id.register_button);
        back_button = findViewById(R.id.backButton);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve user input from EditText fields
                String name = customerName.getText().toString().trim();
                String email = user_nic.getText().toString().trim();
                String joinedDate = customerNic.getText().toString().trim();
                String number = user_number.getText().toString().trim();
                String password = customerpassword.getText().toString().trim();
                String dob = customerdob.getText().toString().trim();
                String address = customerAddress.getText().toString().trim();
                String gender = customerGender.getText().toString().trim();

                // Validate user input for all fields
                if (name.isEmpty() || email.isEmpty() || joinedDate.isEmpty() || number.isEmpty() || password.isEmpty() || dob.isEmpty() || address.isEmpty() || gender.isEmpty()) {
                    // Handle validation errors, e.g., show error messages to the user
                    Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return; // Stop further processing
                }

                // Create a JSON object with user registration data
                JSONObject requestData = new JSONObject();
                try {
                    requestData.put("name", name);
                    requestData.put("email", email);
                    requestData.put("password", password);
                    requestData.put("nic", joinedDate);
                    requestData.put("dob", dob);
                    requestData.put("address", address);
                    requestData.put("gender", gender);
                    requestData.put("number", number);
                    requestData.put("userRole", "Traveler");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Create a request queue for Volley
                RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);

                // Create a JsonObjectRequest with POST method to register the user
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, REGISTER_URL, requestData,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Handle a successful registration response
                                Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                // Optionally, navigate to the login activity
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle errors, such as network issues or server errors
                                if (error instanceof NoConnectionError || error instanceof TimeoutError) {
                                    // Network issues
                                    Toast.makeText(RegisterActivity.this, "Network error. Please check your connection.", Toast.LENGTH_SHORT).show();
                                } else if (error.networkResponse != null) {
                                    // Server error (non-null networkResponse)
                                    int statusCode = error.networkResponse.statusCode;
                                    String errorMessage = new String(error.networkResponse.data);
                                    Toast.makeText(RegisterActivity.this, "Server error: " + statusCode + " - " + errorMessage, Toast.LENGTH_SHORT).show();
                                } else {
                                    // Other errors
                                    Toast.makeText(RegisterActivity.this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                // Add the request to the request queue
                requestQueue.add(request);
            }
        });


        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent1 = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent1);
            }
        });
    }
}
