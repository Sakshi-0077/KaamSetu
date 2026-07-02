package com.example.kaamsetuog;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordOfMouthActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    WomAdapter adapter;
    List<Worker> allWorkerList;
    List<Worker> filteredList;
    ChipGroup categoryChipGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordofmouth);

        recyclerView = findViewById(R.id.recyclerWorkers);
        categoryChipGroup = findViewById(R.id.categoryChipGroup);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        allWorkerList = new ArrayList<>();
        filteredList = new ArrayList<>();

        loadWorkers();

        filteredList.addAll(allWorkerList);
        adapter = new WomAdapter(this, filteredList);
        recyclerView.setAdapter(adapter);

        categoryChipGroup.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (checkedIds.isEmpty()) return;

            int checkedId = checkedIds.get(0);
            Chip chip = findViewById(checkedId);
            String category = chip.getText().toString();
            filterWorkers(category);
        });
    }

    private void filterWorkers(String category) {
        filteredList.clear();
        if (category.equalsIgnoreCase("All")) {
            filteredList.addAll(allWorkerList);
        } else {
            for (Worker worker : allWorkerList) {
                if (worker.work != null && worker.work.equalsIgnoreCase(category)) {
                    filteredList.add(worker);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void loadWorkers() {
        // Electricians
        allWorkerList.add(new Worker("Mahesh V.", "Electrician", "9876543217", "12 yrs exp", "4.9", "38", "Available", true, "Priya Kapoor", "Fixed all wiring issues in our new flat quickly. Very honest about pricing.", Arrays.asList("Wiring", "AC installation", "Short circuit repair"), "Priya, Ankit +1 from your contacts hired him", Arrays.asList("Priya Kapoor", "Ankit Verma", "Suresh Gupta"), R.drawable.login));
        allWorkerList.add(new Worker("Vijay K.", "Electrician", "9876543218", "3 yrs exp", "4.0", "12", "Available", true, "Anuj T.", "Quick response and polite.", Arrays.asList("Fan Repair", "Switch Replacement"), "Anuj from your contacts hired him", Arrays.asList("Anuj T."), R.drawable.login));
        allWorkerList.add(new Worker("Prakash Joshi", "Electrician", "9876543219", "11 yrs exp", "4.8", "55", "Available", true, "Karan S.", "Very professional work.", Arrays.asList("Inverter", "MCB Box"), "Karan hired him", Arrays.asList("Karan S."), R.drawable.login));

        // Plumbers
        allWorkerList.add(new Worker("Arjun Patel", "Plumber", "9876543210", "8 yrs exp", "4.7", "42", "Available", true, "Meera J.", "Fixed the tap issue perfectly.", Arrays.asList("Drainage", "Tap Repair", "Water Tank"), "Meera hired him", Arrays.asList("Meera J."), R.drawable.login));
        allWorkerList.add(new Worker("Deepak Yadav", "Plumber", "9876543212", "12 yrs exp", "4.9", "61", "Available", true, "Rahul Nair", "Excellent pipe fitting work.", Arrays.asList("Pipe fitting", "Leak repair"), "Rahul from your contacts hired him", Arrays.asList("Rahul Nair"), R.drawable.login));

        // Carpenters
        allWorkerList.add(new Worker("Rajesh P.", "Carpenter", "9876543222", "15 yrs exp", "4.7", "78", "Available", true, "Neha Shah", "Beautifully made our kitchen cabinets.", Arrays.asList("Furniture", "Woodwork", "Polishing"), "Neha Shah from your contacts hired him", Arrays.asList("Neha Shah"), R.drawable.login));
        allWorkerList.add(new Worker("Lokesh Pandey", "Carpenter", "9876543226", "10 yrs exp", "4.7", "45", "Available", true, "Ankit V.", "Great attention to detail.", Arrays.asList("Repair", "Door Fitting"), "Ankit hired him", Arrays.asList("Ankit V."), R.drawable.login));

        // Tutors
        allWorkerList.add(new Worker("Prof. Sharma", "Tutor", "9876543248", "12 yrs exp", "4.9", "120", "Available", true, "Ankit Verma", "Excellent math teacher for my son.", Arrays.asList("Mathematics", "Science", "Home Tuition"), "Ankit hired him", Arrays.asList("Ankit Verma"), R.drawable.login));

        // Beauticians
        allWorkerList.add(new Worker("Neha Gupta", "Beautician", "9876543254", "11 yrs exp", "4.9", "82", "Available", true, "Deepa Rai", "Very gentle and skilled.", Arrays.asList("Makeup", "Hair Styling", "Skincare"), "Deepa hired her", Arrays.asList("Deepa Rai"), R.drawable.login));

        // Mechanics
        allWorkerList.add(new Worker("Auto Raj", "Mechanic", "9876543250", "10 yrs exp", "4.6", "95", "Available", true, "Suresh G.", "Fixed my car's engine issue.", Arrays.asList("Car Repair", "Engine Tuning"), "Suresh hired him", Arrays.asList("Suresh G."), R.drawable.login));
    }
}
