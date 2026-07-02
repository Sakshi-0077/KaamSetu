package com.example.kaamsetuog;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class WorkerDetailsActivity extends AppCompatActivity {

    private Worker worker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_details);

        worker = (Worker) getIntent().getSerializableExtra("worker");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        ImageView image = findViewById(R.id.detailImage);
        TextView name = findViewById(R.id.detailName);
        TextView rating = findViewById(R.id.detailRating);
        TextView exp = findViewById(R.id.detailExp);
        TextView phone = findViewById(R.id.detailPhone);
        Button btnSave = findViewById(R.id.btnSave);

        if (worker != null) {
            image.setImageResource(worker.getImage());
            name.setText(worker.getName());
            rating.setText(worker.getRating());
            exp.setText(worker.getExperience());
            phone.setText(worker.getPhone());
        }

        btnSave.setOnClickListener(v -> {
            saveWorker(worker);
        });
    }

    private void saveWorker(Worker worker) {
        SharedPreferences sp = getSharedPreferences("saved_workers", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("worker_list", null);
        Type type = new TypeToken<ArrayList<Worker>>() {}.getType();
        List<Worker> workerList = gson.fromJson(json, type);

        if (workerList == null) {
            workerList = new ArrayList<>();
        }

        // Check if already saved
        boolean exists = false;
        for (Worker w : workerList) {
            if (w.getName().equals(worker.getName()) && w.getPhone().equals(worker.getPhone())) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            workerList.add(worker);
            String updatedJson = gson.toJson(workerList);
            sp.edit().putString("worker_list", updatedJson).apply();
            Toast.makeText(this, "Worker Saved!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Already Saved!", Toast.LENGTH_SHORT).show();
        }
    }
}