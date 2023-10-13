package com.example.mad;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.Objects;

public class UpdateProfileActivity extends AppCompatActivity {

    private ImageButton imageBackButtonEditOrder2;
    private EditText customerNameLayout;
    private EditText emailLayout;
    private EditText phoneLayout;
    private EditText customerNic;
    private EditText profilePasswordLayout;
    private EditText profileDobLayout;
    private EditText profileAddressLayout;
    private EditText profileGenderLayout;
    private ImageButton saveButton;
    private RequestQueue requestQueue;

    private static final String UPDATE_URL = "https://10.0.2.2:8000/Account/profile/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        imageBackButtonEditOrder2 = findViewById(R.id.imageBackButtonEditOrder2);
        customerNameLayout = findViewById(R.id.customerName1);
        emailLayout = findViewById(R.id.user_email);
        phoneLayout = findViewById(R.id.user_number);
        customerNic = findViewById(R.id.customerNic);
        profilePasswordLayout = findViewById(R.id.customerpassword);
        profileDobLayout = findViewById(R.id.customerdob);
        profileAddressLayout = findViewById(R.id.customerAddress);
        profileGenderLayout = findViewById(R.id.customerGender);
        saveButton = findViewById(R.id.update_button);

        // Retrieve the values from the Intent
        String Id = getIntent().getStringExtra("Id");
        String Name = getIntent().getStringExtra("Name");
        String Email = getIntent().getStringExtra("Email");
        String Nic = getIntent().getStringExtra("Nic");
        String Number = getIntent().getStringExtra("Number");
        String Dob = getIntent().getStringExtra("Dob");
        String Address = getIntent().getStringExtra("Address");
        String Gender = getIntent().getStringExtra("Gender");

        // Set the retrieved values to the corresponding EditText fields
        customerNameLayout.setText(Name);
        emailLayout.setText(Email);
        phoneLayout.setText(Number);
        customerNic.setText(Nic);
        profileDobLayout.setText(Dob);
        profileAddressLayout.setText(Address);
        profileGenderLayout.setText(Gender);

        // Initialize the RequestQueue for making network requests using Volley.
        requestQueue = Volley.newRequestQueue(this);

        imageBackButtonEditOrder2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the back button click to navigate back.
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve user input from the TextInputLayouts.
                String customerName = customerNameLayout.getText().toString().trim();
                String email = emailLayout.getText().toString().trim();
                String phone = phoneLayout.getText().toString().trim();
                String joinedDate = customerNic.getText().toString().trim();
                String password = profilePasswordLayout.getText().toString().trim();
                String dob = profileDobLayout.getText().toString().trim();
                String address = profileAddressLayout.getText().toString().trim();
                String gender = profileGenderLayout.getText().toString().trim();

                // Perform client-side validation here (e.g., check if required fields are not empty).

                // Construct a JSON object to send as the request body.
                JSONObject requestBody = new JSONObject();
                try {
                    requestBody.put("customerName", customerName);
                    requestBody.put("email", email);
                    requestBody.put("phone", phone);
                    requestBody.put("joinedDate", joinedDate);
                    requestBody.put("password", password);
                    requestBody.put("dob", dob);
                    requestBody.put("address", address);
                    requestBody.put("gender", gender);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Make a PUT request to update the user's profile on the backend.
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, UPDATE_URL + Id, requestBody,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Handle a successful response from the backend.
                                DatabaseHelper db = new DatabaseHelper(UpdateProfileActivity.this);
                                db.getProfileData(Id);

                                // Provide clear feedback to the user.
                                Toast.makeText(UpdateProfileActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                                finish(); // Finish the activity after a successful update.
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle network or server errors.
                                if (error instanceof NoConnectionError || error instanceof TimeoutError) {
                                    // Network issues
                                    Toast.makeText(UpdateProfileActivity.this, "Network error. Please check your connection.", Toast.LENGTH_SHORT).show();
                                } else if (error.networkResponse != null) {
                                    // Server error (non-null networkResponse)
                                    int statusCode = error.networkResponse.statusCode;
                                    String errorMessage = new String(error.networkResponse.data);
                                    Toast.makeText(UpdateProfileActivity.this, "Server error: " + statusCode + " - " + errorMessage, Toast.LENGTH_SHORT).show();
                                } else {
                                    // Other errors
                                    Toast.makeText(UpdateProfileActivity.this, "Update failed. Please try again.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                // Add the request to the RequestQueue to execute it.
                requestQueue.add(request);
            }
        });
    }
}
