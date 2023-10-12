package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mad.adapters.ReservationAdapter;
import com.example.mad.models.ReservationItem;

import java.util.ArrayList;
import java.util.List;

public class MyBookingsActivity extends AppCompatActivity {

    ImageButton navhomebutton, bookingsbutton, profilebutton;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

        navhomebutton = findViewById(R.id.navhome);
        bookingsbutton = findViewById(R.id.bookings);
        profilebutton = findViewById(R.id.profile);
        recyclerView = findViewById(R.id.orderList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<ReservationItem> reservationItems = createSampleReservationData();
        ReservationAdapter adapter = new ReservationAdapter(this, reservationItems);
        recyclerView.setAdapter(adapter);

        navhomebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent1 = new Intent(MyBookingsActivity.this, MainHomeActivity.class);
                startActivity(intent1);
            }
        });

        bookingsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent1 = new Intent(MyBookingsActivity.this, MyBookingsActivity.class);
                startActivity(intent1);
            }
        });

        profilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent1 = new Intent(MyBookingsActivity.this, MainProfileActivity.class);
                startActivity(intent1);
            }
        });

    }

    private List<ReservationItem> createSampleReservationData() {
        List<ReservationItem> reservationItems = new ArrayList<>();
        reservationItems.add(new ReservationItem(1, "RES-001", "City X", "City Y", "9:00 AM", "Train A"));
        reservationItems.add(new ReservationItem(2, "RES-002", "City Y", "City Z", "11:00 AM", "Train B"));
        // Add more sample data as needed
        return reservationItems;
    }
}