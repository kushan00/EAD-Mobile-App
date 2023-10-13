package com.example.mad.handlers;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mad.models.Profile;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ProfileDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_PROFILE = "profile";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_NIC = "nic";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_NUMBER = "number";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_DOB = "dob";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_IS_ACTIVE = "isActive";
    private static final String COLUMN_USER_ROLE = "userRole";
    private static final String COLUMN_CREATED_TIME = "createdTime";

    private static final String USER_DETAILS_URL = "https://10.0.2.2:8000/Account/profile/";

    private Context mContext; // Add a Context variable

    // Constructor that accepts a Context
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context; // Initialize mContext
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create your profile table here
        String createProfileTable = "CREATE TABLE IF NOT EXISTS " + TABLE_PROFILE + " (" +
                COLUMN_ID + " INTEGER, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_NIC + " TEXT PRIMARY KEY, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_NUMBER + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_DOB + " TEXT, " +
                COLUMN_GENDER + " TEXT, " +
                COLUMN_IS_ACTIVE + " INTEGER, " +
                COLUMN_USER_ROLE + " TEXT, " +
                COLUMN_CREATED_TIME + " TEXT" +
                ")";
        db.execSQL(createProfileTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades, if needed
    }

    public long insertProfile(Profile profile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, profile.getId());
        values.put(COLUMN_NAME, profile.getName());
        values.put(COLUMN_NIC, profile.getNic());
        values.put(COLUMN_ADDRESS, profile.getAddress());
        values.put(COLUMN_NUMBER, profile.getNumber());
        values.put(COLUMN_EMAIL, profile.getEmail());
        values.put(COLUMN_DOB, profile.getDob());
        values.put(COLUMN_GENDER, profile.getGender());
        values.put(COLUMN_IS_ACTIVE, profile.isActive() ? 1 : 0);
        values.put(COLUMN_USER_ROLE, profile.getUserRole());
        values.put(COLUMN_CREATED_TIME, profile.getCreatedTime());

        long rowId = db.insert(TABLE_PROFILE, null, values);
        db.close();
        return rowId;
    }

    @SuppressLint("Range")
    public Profile getFirstProfile() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {
                COLUMN_ID, COLUMN_NAME, COLUMN_NIC, COLUMN_ADDRESS,
                COLUMN_NUMBER, COLUMN_EMAIL, COLUMN_DOB, COLUMN_GENDER,
                COLUMN_IS_ACTIVE, COLUMN_USER_ROLE, COLUMN_CREATED_TIME
        };

        Cursor cursor = db.query(TABLE_PROFILE, columns, null, null, null, null, null, null);

        Profile profile = null;

        if (cursor != null && cursor.moveToFirst()) {
            profile = new Profile();
            profile.setId(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
            profile.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            profile.setNic(cursor.getString(cursor.getColumnIndex(COLUMN_NIC)));
            profile.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
            profile.setNumber(cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER)));
            profile.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
            profile.setDob(cursor.getString(cursor.getColumnIndex(COLUMN_DOB)));
            profile.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)));
            profile.setActive(cursor.getInt(cursor.getColumnIndex(COLUMN_IS_ACTIVE)) == 1);
            profile.setUserRole(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ROLE)));
            profile.setCreatedTime(cursor.getString(cursor.getColumnIndex(COLUMN_CREATED_TIME)));
        }

        if (cursor != null) {
            cursor.close();
        }

        db.close();

        return profile;
    }

    public void deleteDatabase() {
        // Get the database path
        String dbPath = mContext.getDatabasePath(DATABASE_NAME).getPath();

        // Close any open database connections
        close();

        // Delete the database file
        SQLiteDatabase.deleteDatabase(new File(dbPath));
    }

    // Method to insert dummy values into the database
    public void insertDummyData() {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, "Saman Perera");
        values.put(COLUMN_EMAIL, "saman@test.lk");
        values.put(COLUMN_NIC, "199645866923");
        values.put(COLUMN_NUMBER, "0719576341");
        values.put(COLUMN_DOB, "1998-10-12");
        values.put(COLUMN_ADDRESS, "Colombo");
        values.put(COLUMN_GENDER, "Male");
        values.put(COLUMN_IS_ACTIVE, 1); // Assuming 1 represents 'true' for isActive
        values.put(COLUMN_USER_ROLE, "User");
        values.put(COLUMN_CREATED_TIME, "2023-10-13T06:13:23.900Z");

        long rowId = db.insert(TABLE_PROFILE, null, values);

        db.close();
    }

    // Function to make a GET request for profile data
    public void getProfileData(String id) {
        // Create a request queue for Volley
        RequestQueue profileRequestQueue = Volley.newRequestQueue(mContext);

        JsonObjectRequest profileRequest = new JsonObjectRequest(Request.Method.GET, USER_DETAILS_URL + id, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject profileResponse) {
                        try {
                            // Store it in the SQLite database using your DatabaseHelper
                            Profile profile = new Profile();
                            profile.setId(profileResponse.getString("id"));
                            profile.setName(profileResponse.getString("name"));
                            profile.setEmail(profileResponse.getString("email"));
                            profile.setNic(profileResponse.getString("nic"));
                            profile.setAddress(profileResponse.getString("address"));
                            profile.setNumber(profileResponse.getString("number"));
                            profile.setDob(profileResponse.getString("dob"));
                            profile.setGender(profileResponse.getString("gender"));
                            profile.setActive(profileResponse.getBoolean("isActive"));
                            profile.setUserRole(profileResponse.getString("userRole"));
                            profile.setCreatedTime(profileResponse.getString("createdTime"));
                            // Set other profile fields

                            deleteDatabase();
                            // Add the profile to the database
                            long rowId = insertProfile(profile);

                            if (rowId != -1) {
                                // Data was successfully inserted
                                Toast.makeText(mContext, "Profile saved to the database", Toast.LENGTH_SHORT).show();
                            } else {
                                // Failed to insert data
                                Toast.makeText(mContext, "Failed to save profile", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors
                        error.printStackTrace();
                    }
                }
        );

        // Add the profile request to the profileRequestQueue
        profileRequestQueue.add(profileRequest);
    }
}
