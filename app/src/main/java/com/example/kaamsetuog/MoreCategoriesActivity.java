package com.example.kaamsetuog;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MoreCategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_categories);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        RecyclerView recyclerView = findViewById(R.id.moreCatRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        List<Category> list = new ArrayList<>();
        list.add(new Category(R.drawable.gardener, "Gardener"));
        list.add(new Category(R.drawable.mechanic, "Mechanic"));
        list.add(new Category(R.drawable.maid, "Maid"));
        list.add(new Category(R.drawable.tutor, "Tutor"));
        list.add(new Category(R.drawable.tailor, "Tailor"));
        list.add(new Category(R.drawable.beautician, "Beautician"));
        list.add(new Category(R.drawable.login, "default"));
        CategoryAdapter adapter = new CategoryAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }
}