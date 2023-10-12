package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import android.widget.EditText;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

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
// Create a custom TrustManager that trusts all certificates
//        TrustManager[] trustAllCertificates = new TrustManager[]{
//                new X509TrustManager() {
//                    @Override
//                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//                    }
//
//                    @Override
//                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//                    }
//
//                    @Override
//                    public X509Certificate[] getAcceptedIssuers() {
//                        return new X509Certificate[0];
//                    }
//                }
//        };

//// Create an SSLContext with the custom TrustManager
//        try {
//            SSLContext sslContext = SSLContext.getInstance("TLS");
//            sslContext.init(null, trustAllCertificates, new SecureRandom());
//
//            // Set the SSL socket factory for OkHttpClient
//            OkHttpClient.Builder builder = new OkHttpClient.Builder()
//                    .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCertificates[0])
//                    .hostnameVerifier((hostname, session) -> true);
//
//            // Use this OkHttpClient for your requests
//            OkHttpClient client = builder.build();
//
//            // Create your request with this client
//            // ...
//
//        } catch (NoSuchAlgorithmException | KeyManagementException e) {
//            e.printStackTrace();
//        }
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
//                String nic = nicEditText.getText().toString();
//                String password = passwordEditText.getText().toString();
//                // Create a JSON object with user data
//                JSONObject requestData = new JSONObject();
//                try {
//                    requestData.put("nic", nic);
//                    requestData.put("password", password);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                // Create a request queue for Volley
//                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
//
//                // Create a JsonObjectRequest with POST method
//                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, LOGIN_URL, requestData,
//                        new Response.Listener<JSONObject>() {
//                            @Override
//                            public void onResponse(JSONObject response) {
//                                try {
//                                    boolean isAuthenticated = response.getBoolean("authenticated");
//                                    if (isAuthenticated) {
                                        // User authenticated successfully, navigate to the next activity
                                        Intent intent = new Intent(LoginActivity.this, MainHomeActivity.class);
                                        startActivity(intent);
//                                    } else {
//                                        // Authentication failed, show an error message
//                                        // You can display an error message to the user
//                                    }
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                // Handle network or other errors here
//                                error.printStackTrace();
//                            }
//                        }
//                );
//
//                // Add the request to the request queue
//                requestQueue.add(request);
            }
        });
    }
}