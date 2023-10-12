package com.example.mad;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mad.adapters.ScheduleAdapter;
import com.example.mad.models.ScheduleItem;

import java.util.ArrayList;
import java.util.List;

public class ScheduleViewActivity extends AppCompatActivity {

    ImageButton navhomebutton, bookingsbutton, profilebutton;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_view);

        navhomebutton = findViewById(R.id.navhome);
        bookingsbutton = findViewById(R.id.bookings);
        profilebutton = findViewById(R.id.profile);
        recyclerView = findViewById(R.id.orderList);

        // Set up the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<ScheduleItem> scheduleItems = createSampleScheduleData();
        ScheduleAdapter adapter = new ScheduleAdapter(this, scheduleItems);
        recyclerView.setAdapter(adapter);

        // Set click listeners for the buttons
        navhomebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent1 = new Intent(ScheduleViewActivity.this, MainHomeActivity.class);
                startActivity(intent1);
            }
        });

        bookingsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent1 = new Intent(ScheduleViewActivity.this, MyBookingsActivity.class);
                startActivity(intent1);
            }
        });

        profilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent1 = new Intent(ScheduleViewActivity.this, MainProfileActivity.class);
                startActivity(intent1);
            }
        });
    };

    // Helper method to create sample schedule data
    private List<ScheduleItem> createSampleScheduleData() {
        List<ScheduleItem> scheduleItems = new ArrayList<>();
        scheduleItems.add(new ScheduleItem(1, "SCH-001", "City A", "City B", "8:00 AM", "Train 1"));
        scheduleItems.add(new ScheduleItem(2, "SCH-002", "City B", "City C", "10:00 AM", "Train 2"));
        // Add more sample data as needed
        return scheduleItems;
    }
}
