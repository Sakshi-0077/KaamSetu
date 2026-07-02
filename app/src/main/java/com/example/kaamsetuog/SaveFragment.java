package com.example.kaamsetuog;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SaveFragment extends Fragment {

    private RecyclerView recyclerView;
    private WorkerAdapter adapter;
    private List<Worker> savedList;
    private TextView noSavedText;

    public SaveFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_save, container, false);

        recyclerView = view.findViewById(R.id.saveRecyclerView);
        noSavedText = view.findViewById(R.id.noSavedText);
        
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        
        loadSavedWorkers();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadSavedWorkers();
    }

    private void loadSavedWorkers() {
        SharedPreferences sp = getActivity().getSharedPreferences("saved_workers", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("worker_list", null);
        Type type = new TypeToken<ArrayList<Worker>>() {}.getType();
        savedList = gson.fromJson(json, type);

        if (savedList == null || savedList.isEmpty()) {
            savedList = new ArrayList<>();
            noSavedText.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            noSavedText.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            adapter = new WorkerAdapter(getContext(), savedList);
            adapter.setOnRemoveClickListener(position -> {
                savedList.remove(position);
                saveListToPreferences();
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(position, savedList.size());
                if (savedList.isEmpty()) {
                    noSavedText.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
            });
            recyclerView.setAdapter(adapter);
        }
    }

    private void saveListToPreferences() {
        SharedPreferences sp = getActivity().getSharedPreferences("saved_workers", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(savedList);
        editor.putString("worker_list", json);
        editor.apply();
    }
}