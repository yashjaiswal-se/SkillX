package com.example.skillx;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SkillAdapter skillAdapter;
    private List<Skill> skillList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Bottom Navigation
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        //bottomNav.setOnNavigationItemSelectedListener(navListener);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.skills_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load sample skills data
        loadSkills();
    }

    private void loadSkills() {
        skillList = new ArrayList<>();
        skillList.add(new Skill("Graphic Design", "Design", 50));
        skillList.add(new Skill("Java Programming", "Coding", 100));
        skillList.add(new Skill("Content Writing", "Writing", 30));
        skillList.add(new Skill("Video Editing", "Media", 75));
        skillList.add(new Skill("Marketing Strategy", "Business", 60));

        skillAdapter = new SkillAdapter(skillList);
        recyclerView.setAdapter(skillAdapter);
    }
/*
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            return true;
                        case R.id.nav_post:
                            return true;
                        case R.id.nav_requests:
                            return true;
                        case R.id.nav_profile:
                            return true;
                    }
                    return false;
                }
            };
            */

}
