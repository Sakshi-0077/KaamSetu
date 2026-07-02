package com.example.kaamsetuog;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ReviewsActivity extends AppCompatActivity {

    ArrayList<String> reviews;
    ArrayAdapter<String> adapter;
    float totalRating = 13.5f; // Initial sample rating sum
    int ratingCount = 3;       // Initial sample rating count

    @Override
    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_reviews);

        EditText nameInput = findViewById(R.id.nameInput);
        EditText reviewInput = findViewById(R.id.reviewInput);
        Button btn = findViewById(R.id.submitReview);
        ListView list = findViewById(R.id.reviewList);
        RatingBar ratingBar = findViewById(R.id.reviewRatingBar);
        TextView avgText = findViewById(R.id.averageRatingText);

        reviews = new ArrayList<>();
        // Pre-adding some sample reviews
        reviews.add("John Doe: Great service, very professional! (5.0★)");
        reviews.add("Sara Smith: Found an electrician within 10 minutes. (4.5★)");
        reviews.add("Rahul V: Excellent platform for local workers. (4.0★)");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reviews);
        list.setAdapter(adapter);

        updateAverage(avgText);

        btn.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String reviewText = reviewInput.getText().toString().trim();
            float rating = ratingBar.getRating();
            
            if (!name.isEmpty() && !reviewText.isEmpty() && rating > 0) {
                String fullReview = name + ": " + reviewText + " (" + rating + "★)";
                reviews.add(0, fullReview);
                adapter.notifyDataSetChanged();
                
                // Update average logic
                totalRating += rating;
                ratingCount++;
                updateAverage(avgText);
                
                nameInput.setText("");
                reviewInput.setText("");
                ratingBar.setRating(0);
                Toast.makeText(this, "Review posted!", Toast.LENGTH_SHORT).show();
            } else if (rating == 0) {
                Toast.makeText(this, "Please provide a star rating", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please enter both name and review", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateAverage(TextView view) {
        float avg = totalRating / ratingCount;
        view.setText(String.format("Average Rating: %.1f", avg));
    }
}
