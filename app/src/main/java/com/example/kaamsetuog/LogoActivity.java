package com.example.kaamsetuog;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class LogoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        // Splash screen delay of 2 seconds
        new Handler().postDelayed(() -> {
            // FOR TESTING: Commenting out the login check to always show LoginActivity
            /*
            SharedPreferences sp = getSharedPreferences("user_data", Context.MODE_PRIVATE);
            boolean isLoggedIn = sp.getBoolean("is_logged_in", false);

            Intent intent;
            if (isLoggedIn) {
                intent = new Intent(LogoActivity.this, MainActivity.class);
            } else {
                intent = new Intent(LogoActivity.this, LoginActivity.class);
            }
            */
            Intent intent = new Intent(LogoActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, 2000);
    }
}