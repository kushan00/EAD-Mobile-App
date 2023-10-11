package com.example.mad;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainHomeActivity extends AppCompatActivity {

    ImageButton add_button, navhomebutton, bookingsbutton, profilebutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        navhomebutton = findViewById(R.id.navhome);
        bookingsbutton = findViewById(R.id.bookings);
        profilebutton = findViewById(R.id.profile);
        add_button = findViewById(R.id.update_button2);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainHomeActivity.this, ScheduleViewActivity.class);
                startActivity(intent);
            }
        });




        navhomebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent1 = new Intent(MainHomeActivity.this, MainHomeActivity.class);
                startActivity(intent1);
            }
        });

        bookingsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent1 = new Intent(MainHomeActivity.this, MyBookingsActivity.class);
                startActivity(intent1);
            }
        });

        profilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent1 = new Intent(MainHomeActivity.this, MainProfileActivity.class);
                startActivity(intent1);
            }
        });

    }



}