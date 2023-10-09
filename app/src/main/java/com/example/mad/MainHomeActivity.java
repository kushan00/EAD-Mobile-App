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

    RecyclerView recyclerView;
    ImageButton add_button, cus, order, sup;
    TextView countd;
    MyDatabaseHelper myDB;
    ArrayList<String> stockID, stockName, stockCategory, stockQuantity, stockUnitPrice;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        recyclerView = findViewById(R.id.list_item);
        countd = findViewById(R.id.count);
        cus = findViewById(R.id.cus);
        order = findViewById(R.id.order);
        sup = findViewById(R.id.sup);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainHomeActivity.this, AddStock.class);
                startActivity(intent);
            }
        });


        myDB = new MyDatabaseHelper(MainHomeActivity.this);
        stockID = new ArrayList<>();
        stockName = new ArrayList<>();
        stockCategory = new ArrayList<>();
        stockQuantity = new ArrayList<>();
        stockUnitPrice = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainHomeActivity.this,this, stockID, stockName, stockCategory, stockQuantity, stockUnitPrice);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainHomeActivity.this));

        cus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent1 = new Intent(MainHomeActivity.this, CustomerMainActivity.class);
                startActivity(intent1);
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent1 = new Intent(MainHomeActivity.this, OrderMainActivity.class);
                startActivity(intent1);
            }
        });

        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent1 = new Intent(MainHomeActivity.this, SupplierMainActivity.class);
                startActivity(intent1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No Data.",Toast.LENGTH_LONG).show();
        }else{
            int count = cursor.getCount();
            Toast.makeText(this,String.valueOf(count),Toast.LENGTH_SHORT).show();
            countd.setText(String.valueOf(count));
            while (cursor.moveToNext()){
                stockID.add(cursor.getString(0));
                stockName.add(cursor.getString(1));
                stockCategory.add(cursor.getString(2));
                stockQuantity.add(cursor.getString(3));
                stockUnitPrice.add(cursor.getString(4));
            }
        }
    }

}