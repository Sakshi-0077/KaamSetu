package com.example.kaamsetuog;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView heyUser = view.findViewById(R.id.heyUser);

        SharedPreferences sp = getActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE);
        String name = sp.getString("username", "User");
        String city = sp.getString("city", "Ahmedabad");
        heyUser.setText("Hey " + name);

        TextView location = view.findViewById(R.id.location);
        location.setText(city);

        ImageButton wordBtn = view.findViewById(R.id.profile_image2);
        ImageButton filterBtn = view.findViewById(R.id.filterBtn);

        wordBtn.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), WordOfMouthActivity.class));
        });
        filterBtn.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), FilterActivity.class));
        });

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));

        List<Category> list = new ArrayList<>();
        list.add(new Category(R.drawable.plumber, "Plumber"));
        list.add(new Category(R.drawable.electrician, "Electrician"));
        list.add(new Category(R.drawable.painter, "Painter"));
        list.add(new Category(R.drawable.cleaner, "Cleaner"));
        list.add(new Category(R.drawable.carpenter, "Carpenter"));
        list.add(new Category(R.drawable.driver, "Driver"));
        list.add(new Category(R.drawable.cook, "Cook"));
        list.add(new Category(R.drawable.ic8, "More"));

        CategoryAdapter adapter = new CategoryAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);

        android.widget.EditText searchEditText = view.findViewById(R.id.searchEditText);
        searchEditText.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString(), list, adapter);
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {}
        });

        RecyclerView recyclerView2 = view.findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        );

        List<Worker> suggestedWorkers = new ArrayList<>();
        suggestedWorkers.add(new Worker("Mahesh V.", "⭐ 4.9", "12 yrs exp", R.drawable.login, "9876543217"));
        suggestedWorkers.add(new Worker("Nitin Malhotra", "⭐ 4.9", "15 yrs exp", R.drawable.login, "9876543235"));
        suggestedWorkers.add(new Worker("Sita Devi", "⭐ 4.8", "8 yrs exp", R.drawable.login, "9876543227"));
        suggestedWorkers.add(new Worker("Arjun Patel", "⭐ 4.7", "8 yrs exp", R.drawable.login, "9876543210"));
        suggestedWorkers.add(new Worker("Rajesh P.", "⭐ 4.7", "15 yrs exp", R.drawable.login, "9876543222"));
        suggestedWorkers.add(new Worker("Karan J.", "⭐ 4.7", "10 yrs exp", R.drawable.login, "9876543241"));

        WorkerAdapter workerAdapter = new WorkerAdapter(getContext(), suggestedWorkers);
        recyclerView2.setAdapter(workerAdapter);

        TextView moreSuggest = view.findViewById(R.id.more);
        moreSuggest.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CategoryActivity.class);
            intent.putExtra("category", "Suggested");
            startActivity(intent);
        });

        return view;
    }

    private void filter(String text, List<Category> list, CategoryAdapter adapter) {
        List<Category> allCategories = new ArrayList<>(list);
        allCategories.add(new Category(R.drawable.gardener, "Gardener"));
        allCategories.add(new Category(R.drawable.mechanic, "Mechanic"));
        allCategories.add(new Category(R.drawable.maid, "Maid"));
        allCategories.add(new Category(R.drawable.tutor, "Tutor"));
        allCategories.add(new Category(R.drawable.tailor, "Tailor"));
        allCategories.add(new Category(R.drawable.beautician, "Beautician"));

        if (text.isEmpty()) {
            adapter.updateList(list);
            return;
        }

        List<Category> filteredList = new ArrayList<>();
        for (Category item : allCategories) {
            if (!item.name.equals("More") && item.name.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.updateList(filteredList);
    }
}