package com.example.mad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import com.example.mad.models.Profile;

import org.json.JSONObject;

public class MainProfileActivity extends AppCompatActivity {

    ImageButton navhomebutton, bookingsbutton, profilebutton;
    EditText customerName;
    EditText user_email;
    EditText user_nic_profile;
    EditText user_number;
    EditText customerdob;
    EditText customerAddress;
    EditText customerGender;
    ImageButton editButton;
    ImageButton deleteButton;
    ImageButton back_button;
    ImageButton logoutButton;

    private static final String USER_DETAILS_URL = "https://10.0.2.2:8000/Account/profile/Status/";
    private static String Id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);

        navhomebutton = findViewById(R.id.navhome);
        editButton = findViewById(R.id.edit_button);
        deleteButton = findViewById(R.id.delete_button);
        bookingsbutton = findViewById(R.id.bookings);
        profilebutton = findViewById(R.id.profile);
        back_button = findViewById(R.id.backButton);
        logoutButton = findViewById(R.id.logoutButton);

        customerName = findViewById(R.id.customerName1);
        user_email = findViewById(R.id.user_email);
        user_nic_profile = findViewById(R.id.user_nic_profile);
        user_number = findViewById(R.id.user_number);
        customerdob = findViewById(R.id.customerdob);
        customerAddress = findViewById(R.id.user_address_profile);
        customerGender = findViewById(R.id.user_gender_profile);

        DatabaseHelper db = new DatabaseHelper(MainProfileActivity.this);
        Profile profile = db.getFirstProfile();

//        if (profile == null) {
//            db.insertDummyData();
//            profile = db.getFirstProfile();
//        }

        if (profile != null) {
            Id = profile.getId();
            customerName.setText(profile.getName());
            user_email.setText(profile.getEmail());
            user_nic_profile.setText(profile.getNic());
            user_number.setText(profile.getNumber());
            customerdob.setText(profile.getDob());
            customerAddress.setText(profile.getAddress());
            customerGender.setText(profile.getGender());
        }

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent = new Intent(MainProfileActivity.this, UpdateProfileActivity.class);

                // Pass current values as extras
                String updatedName = customerName.getText().toString();
                String updatedEmail = user_email.getText().toString();
                String updatedNic = user_nic_profile.getText().toString();
                String updatedNumber = user_number.getText().toString();
                String updatedDob = customerdob.getText().toString();
                String updatedAddress = customerAddress.getText().toString();
                String updatedGender = customerGender.getText().toString();

                // Create an intent to send back the updated values
                intent.putExtra("Id", profile != null ? profile.getId() : null);
                intent.putExtra("Name", updatedName);
                intent.putExtra("Email", updatedEmail);
                intent.putExtra("Nic", updatedNic);
                intent.putExtra("Number", updatedNumber);
                intent.putExtra("Dob", updatedDob);
                intent.putExtra("Address", updatedAddress);
                intent.putExtra("Gender", updatedGender);
                // Add more values as needed
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                deleteProfile();
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent1 = new Intent(MainProfileActivity.this, MainHomeActivity.class);
                startActivity(intent1);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                DatabaseHelper db = new DatabaseHelper(MainProfileActivity.this);
                db.deleteDatabase();
                Intent intent1 = new Intent(MainProfileActivity.this, LoginActivity.class);
                startActivity(intent1);
            }
        });

        navhomebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent1 = new Intent(MainProfileActivity.this, MainHomeActivity.class);
                startActivity(intent1);
            }
        });

        bookingsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent1 = new Intent(MainProfileActivity.this, MyBookingsActivity.class);
                startActivity(intent1);
            }
        });

        profilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent1 = new Intent(MainProfileActivity.this, MainProfileActivity.class);
                startActivity(intent1);
            }
        });

    }

    private void deleteProfile() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Deactivation");
        builder.setMessage("Are you sure you want to deactivate your profile? This action cannot be undone.");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User confirmed the deletion.

                // Create a DELETE request
                JsonObjectRequest deleteRequest = new JsonObjectRequest(Request.Method.PUT, USER_DETAILS_URL + Id, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Handle a successful response from the backend.
                                Toast.makeText(MainProfileActivity.this, "Profile deleted successfully", Toast.LENGTH_SHORT).show();

                                // You can navigate to the login activity or perform other actions.
                                Intent intent1 = new Intent(MainProfileActivity.this, LoginActivity.class);
                                startActivity(intent1);
                                finish(); // Finish the activity after a successful delete.
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Intent intent1 = new Intent(MainProfileActivity.this, LoginActivity.class);
                                startActivity(intent1);
                                // Handle errors, such as network issues or server errors.
                                if (error instanceof NoConnectionError || error instanceof TimeoutError) {
                                    // Network issues
                                    Toast.makeText(MainProfileActivity.this, "Network error. Please check your connection.", Toast.LENGTH_SHORT).show();
                                } else if (error.networkResponse != null) {
                                    // Server error (non-null networkResponse)
                                    int statusCode = error.networkResponse.statusCode;
                                    String errorMessage = new String(error.networkResponse.data);
                                    Toast.makeText(MainProfileActivity.this, "Server error: " + statusCode + " - " + errorMessage, Toast.LENGTH_SHORT).show();
                                } else {
                                    // Other errors
                                    Toast.makeText(MainProfileActivity.this, "Delete failed. Please try again.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                RequestQueue requestQueue = Volley.newRequestQueue(MainProfileActivity.this);
                requestQueue.add(deleteRequest);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User canceled the deletion. Do nothing or provide feedback.
                Toast.makeText(MainProfileActivity.this, "Deletion canceled", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }

}