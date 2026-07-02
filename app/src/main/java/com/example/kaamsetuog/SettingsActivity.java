package com.example.kaamsetuog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    View account, notification, reviews, help, about;
    TextView addAccount, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        account = findViewById(R.id.accountCenter);
        notification = findViewById(R.id.notifications);
        reviews = findViewById(R.id.reviews);
        help = findViewById(R.id.help);
        about = findViewById(R.id.about);
        addAccount = findViewById(R.id.addAccount);
        logout = findViewById(R.id.logout);

        account.setOnClickListener(v -> startActivity(new Intent(this, AccountCenterActivity.class)));
        notification.setOnClickListener(v -> startActivity(new Intent(this, NotificationActivity.class)));
        reviews.setOnClickListener(v -> startActivity(new Intent(this, ReviewsActivity.class)));
        help.setOnClickListener(v -> startActivity(new Intent(this, HelpActivity.class)));
        about.setOnClickListener(v -> startActivity(new Intent(this, AboutActivity.class)));

        addAccount.setOnClickListener(v -> openLogin());
        logout.setOnClickListener(v -> openLogin());
    }

    private void openLogin() {
        android.content.SharedPreferences sp = getSharedPreferences("user_data", android.content.Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("is_logged_in", false);
        editor.apply();

        Intent i = new Intent(this, LoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }
}