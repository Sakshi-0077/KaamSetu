package com.example.kaamsetuog;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FilterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        ImageButton backBtn = findViewById(R.id.backBtn);
        TextView resetBtn = findViewById(R.id.resetBtn);
        Button applyBtn = findViewById(R.id.applyBtn);

        backBtn.setOnClickListener(v -> finish());
        
        resetBtn.setOnClickListener(v -> {
            // Logic to clear selections if needed
            finish();
        });

        applyBtn.setOnClickListener(v -> {
            // Logic to apply filter
            finish();
        });
    }
}
