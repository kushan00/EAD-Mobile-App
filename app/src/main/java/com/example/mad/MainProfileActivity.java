package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainProfileActivity extends AppCompatActivity {

    ImageButton navhomebutton, bookingsbutton, profilebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);

        navhomebutton = findViewById(R.id.navhome);
        bookingsbutton = findViewById(R.id.bookings);
        profilebutton = findViewById(R.id.profile);

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
}