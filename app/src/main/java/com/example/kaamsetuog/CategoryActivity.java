package com.example.kaamsetuog;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WorkerAdapter adapter;
    private List<Worker> workerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        String categoryName = getIntent().getStringExtra("category");

        TextView titleText = findViewById(R.id.categoryTitle);
        titleText.setText(categoryName);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.workersRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        workerList = new ArrayList<>();

        if (categoryName != null) {
            switch (categoryName) {
                case "Plumber":
                    loadPlumbers();
                    break;
                case "Electrician":
                    loadElectricians();
                    break;
                case "Painter":
                    loadPainters();
                    break;
                case "Cleaner":
                    loadCleaners();
                    break;
                case "Carpenter":
                    loadCarpenters();
                    break;
                case "Driver":
                    loadDrivers();
                    break;
                case "Cook":
                    loadCooks();
                    break;
                case "Gardener":
                    loadGardener();
                    break;
                case "Maid":
                    loadMaid();
                    break;
                case "Tutor":
                    loadTutor();
                    break;
                case "Mechanic":
                    loadMechanic();
                    break;
                case "Tailor":
                    loadTailor();
                    break;
                case "Beautician":
                    loadBeautician();
                    break;
                case "Suggested":
                    loadSuggestedWorkers();
                    break;
                default:
                    loadDefaultWorkers();
                    break;
            }
        }
        adapter = new WorkerAdapter(this, workerList);
        recyclerView.setAdapter(adapter);
    }

    private void loadPlumbers() {
        workerList.add(new Worker("Arjun Patel", "⭐ 4.7", "8 yrs exp", R.drawable.login, "9876543210"));
        workerList.add(new Worker("Vikram Singh", "⭐ 4.3", "5 yrs exp", R.drawable.login, "9876543211"));
        workerList.add(new Worker("Deepak Yadav", "⭐ 4.9", "12 yrs exp", R.drawable.login, "9876543212"));
        workerList.add(new Worker("Rajesh Nair", "⭐ 4.1", "3 yrs exp", R.drawable.login, "9876543213"));
        workerList.add(new Worker("Manoj Gupta", "⭐ 4.6", "9 yrs exp", R.drawable.login, "9876543214"));
        workerList.add(new Worker("Sanjay Verma", "⭐ 3.9", "2 yrs exp", R.drawable.login, "9876543215"));
        workerList.add(new Worker("Kiran Reddy", "⭐ 4.4", "6 yrs exp", R.drawable.login, "9876543216"));
    }

    private void loadElectricians() {
        workerList.add(new Worker("Mahesh V.", "⭐ 4.9", "12 yrs exp", R.drawable.login, "9876543217"));
        workerList.add(new Worker("Vijay K.", "⭐ 4.0", "3 yrs exp", R.drawable.login, "9876543218"));
        workerList.add(new Worker("Prakash Joshi", "⭐ 4.8", "11 yrs exp", R.drawable.login, "9876543219"));
        workerList.add(new Worker("Naveen Sharma", "⭐ 4.2", "4 yrs exp", R.drawable.login, "9876543220"));
        workerList.add(new Worker("Sunil Mehta", "⭐ 4.5", "7 yrs exp", R.drawable.login, "9876543221"));
    }

    private void loadCarpenters() {
        workerList.add(new Worker("Rajesh P.", "⭐ 4.7", "15 yrs exp", R.drawable.login, "9876543222"));
        workerList.add(new Worker("Sunil L.", "⭐ 4.3", "5 yrs exp", R.drawable.login, "9876543223"));
        workerList.add(new Worker("Harish Pillai", "⭐ 4.5", "8 yrs exp", R.drawable.login, "9876543224"));
        workerList.add(new Worker("Dinesh Chauhan", "⭐ 4.3", "6 yrs exp", R.drawable.login, "9876543225"));
        workerList.add(new Worker("Lokesh Pandey", "⭐ 4.7", "10 yrs exp", R.drawable.login, "9876543226"));
    }

    private void loadCooks() {
        workerList.add(new Worker("Sita Devi", "⭐ 4.8", "8 yrs exp", R.drawable.login, "9876543227"));
        workerList.add(new Worker("Gopal Das", "⭐ 4.5", "12 yrs exp", R.drawable.login, "9876543228"));
        workerList.add(new Worker("Prapti Mishra", "⭐ 4.6", "11 yrs exp", R.drawable.login, "9876543229"));
        workerList.add(new Worker("Vineeta Saxena", "⭐ 3.7", "1 yr exp", R.drawable.login, "9876543230"));
        workerList.add(new Worker("Ramakrishna B.", "⭐ 4.8", "14 yrs exp", R.drawable.login, "9876543231"));
        workerList.add(new Worker("Sonakshi Jain", "⭐ 4.3", "6 yrs exp", R.drawable.login, "9876543232"));
    }

    private void loadPainters() {
        workerList.add(new Worker("Arjun Singh", "⭐ 4.6", "6 yrs exp", R.drawable.login, "9876543233"));
        workerList.add(new Worker("Bharat Thakur", "⭐ 4.0", "4 yrs exp", R.drawable.login, "9876543234"));
        workerList.add(new Worker("Nitin Malhotra", "⭐ 4.9", "15 yrs exp", R.drawable.login, "9876543235"));
        workerList.add(new Worker("Ajay Kulkarni", "⭐ 4.4", "7 yrs exp", R.drawable.login, "9876543236"));
        workerList.add(new Worker("Santosh Rao", "⭐ 4.2", "5 yrs exp", R.drawable.login, "9876543237"));
    }

    private void loadCleaners() {
        workerList.add(new Worker("Deepak R.", "⭐ 4.4", "2 yrs exp", R.drawable.login, "9876543238"));
        workerList.add(new Worker("Ravi Shankar", "⭐ 4.6", "9 yrs exp", R.drawable.login, "9876543239"));
        workerList.add(new Worker("Ankit Tiwari", "⭐ 4.1", "3 yrs exp", R.drawable.login, "9876543240"));
    }

    private void loadDrivers() {
        workerList.add(new Worker("Karan J.", "⭐ 4.7", "10 yrs exp", R.drawable.login, "9876543241"));
        workerList.add(new Worker("Mohit Dubey", "⭐ 4.8", "13 yrs exp", R.drawable.login, "9876543242"));
        workerList.add(new Worker("Ganesh Iyer", "⭐ 3.8", "2 yrs exp", R.drawable.login, "9876543243"));
    }

    private void loadGardener() {
        workerList.add(new Worker("Ramu Kaka", "⭐ 4.5", "15 yrs exp", R.drawable.login, "9876543244"));
        workerList.add(new Worker("Shyam Lal", "⭐ 4.2", "5 yrs exp", R.drawable.login, "9876543245"));
    }

    private void loadMaid() {
        workerList.add(new Worker("Savitri Bai", "⭐ 4.8", "20 yrs exp", R.drawable.login, "9876543246"));
        workerList.add(new Worker("Laxmi D.", "⭐ 4.4", "8 yrs exp", R.drawable.login, "9876543247"));
    }

    private void loadTutor() {
        workerList.add(new Worker("Prof. Sharma", "⭐ 4.9", "12 yrs exp", R.drawable.login, "9876543248"));
        workerList.add(new Worker("Ms. Priya", "⭐ 4.7", "5 yrs exp", R.drawable.login, "9876543249"));
    }

    private void loadMechanic() {
        workerList.add(new Worker("Auto Raj", "⭐ 4.6", "10 yrs exp", R.drawable.login, "9876543250"));
        workerList.add(new Worker("Billu Mechanic", "⭐ 4.3", "7 yrs exp", R.drawable.login, "9876543251"));
    }
    private void loadTailor() {
        workerList.add(new Worker("Priya Verma", "⭐ 4.8", "9 yrs exp", R.drawable.login, "9876543252"));
        workerList.add(new Worker("Anjali Singh", "⭐ 4.5", "6 yrs exp", R.drawable.login, "9876543253"));
    }
    private void loadBeautician() {
        workerList.add(new Worker("Neha Gupta", "⭐ 4.9", "11 yrs exp", R.drawable.login, "9876543254"));
        workerList.add(new Worker("Ritu Sharma", "⭐ 4.2", "3 yrs exp", R.drawable.login, "9876543255"));
    }

    private void loadSuggestedWorkers() {
        workerList.add(new Worker("Mahesh V.", "⭐ 4.9", "12 yrs exp", R.drawable.login, "9876543217"));
        workerList.add(new Worker("Nitin Malhotra", "⭐ 4.9", "15 yrs exp", R.drawable.login, "9876543235"));
        workerList.add(new Worker("Sita Devi", "⭐ 4.8", "8 yrs exp", R.drawable.login, "9876543227"));
        workerList.add(new Worker("Arjun Patel", "⭐ 4.7", "8 yrs exp", R.drawable.login, "9876543210"));
        workerList.add(new Worker("Rajesh P.", "⭐ 4.7", "15 yrs exp", R.drawable.login, "9876543222"));
        workerList.add(new Worker("Karan J.", "⭐ 4.7", "10 yrs exp", R.drawable.login, "9876543241"));
    }

    private void loadDefaultWorkers() {
        workerList.add(new Worker("General Worker", "⭐ 4.0", "2 yrs exp", R.drawable.login, "9876543256"));
        workerList.add(new Worker("All in One Worker", "⭐ 5.0", "5 yrs exp", R.drawable.login, "9876543257"));
    }
}