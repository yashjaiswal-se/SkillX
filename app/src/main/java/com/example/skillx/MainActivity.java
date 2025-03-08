package com.example.skillx;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SkillAdapter skillAdapter;
    private List<Skill> skillList;
    private EditText searchBar;
    private ImageView profileIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup Toolbar (Top Action Bar)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("SkillX");

        // Profile Icon Click
        profileIcon = findViewById(R.id.profile_icon);
        profileIcon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfileActiviy.class);
            startActivity(intent);
        });

        // Initialize Search Bar
        searchBar = findViewById(R.id.search_bar);

        // Initialize Bottom Navigation
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.skills_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load sample skills data
        loadSkills();
    }

    private void loadSkills() {
        skillList = new ArrayList<>();
        skillList.add(new Skill("Graphic Design", "Design", 50, "Create stunning visuals!", true,
                "android.resource://com.example.skillx/drawable/sample_skill", "Aarav Mehta", 4.8f));
        skillList.add(new Skill("Java Programming", "Coding", 100, "Master Java development!", true,
                "android.resource://com.example.skillx/drawable/sample_skill", "Rohan Sharma", 4.6f));
        skillList.add(new Skill("Content Writing", "Writing", 30, "Write engaging articles!", false,
                "android.resource://com.example.skillx/drawable/sample_skill", "Ishita Verma", 4.2f));
        skillList.add(new Skill("Photography", "Creative Arts", 70, "Capture breathtaking moments!", true,
                "android.resource://com.example.skillx/drawable/sample_skill", "Neha Kapoor", 4.5f));
        skillList.add(new Skill("UI/UX Design", "Design", 90, "Design intuitive and modern interfaces!", true,
                "android.resource://com.example.skillx/drawable/sample_skill", "Kabir Malhotra", 4.7f));
        skillList.add(new Skill("Digital Marketing", "Marketing", 60, "Boost online presence and sales!", true,
                "android.resource://com.example.skillx/drawable/sample_skill", "Sneha Iyer", 4.6f));
        skillList.add(new Skill("Public Speaking", "Communication", 40, "Enhance your speaking skills!", false,
                "android.resource://com.example.skillx/drawable/sample_skill", "Arjun Nair", 4.3f));
        skillList.add(new Skill("Python Development", "Coding", 110, "Develop Python applications!", true,
                "android.resource://com.example.skillx/drawable/sample_skill", "Vikram Desai", 4.9f));
        skillList.add(new Skill("Stock Market Analysis", "Finance", 80, "Learn stock trading and investment!", true,
                "android.resource://com.example.skillx/drawable/sample_skill", "Tanvi Joshi", 4.8f));
        skillList.add(new Skill("Fitness Training", "Health & Wellness", 50, "Get personalized workout plans!", false,
                "android.resource://com.example.skillx/drawable/sample_skill", "Rahul Choudhary", 4.4f));

        skillAdapter = new SkillAdapter(this,skillList);
        recyclerView.setAdapter(skillAdapter);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemId == R.id.nav_post) {
                    Intent intent = new Intent(MainActivity.this, PostSkillActivity.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.nav_requests) {
                    Toast.makeText(MainActivity.this, "Requests", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemId == R.id.nav_profile) {
                    Intent intent = new Intent(MainActivity.this, ProfileActiviy.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            };
}
