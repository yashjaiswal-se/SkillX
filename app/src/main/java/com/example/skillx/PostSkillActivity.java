package com.example.skillx;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PostSkillActivity extends AppCompatActivity {

    private EditText skillTitle, skillDescription, skillCredits;
    private Spinner categorySpinner;
    private Switch barterSwitch;
    private ImageView skillImage;
    private Button postButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_skill);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize UI
        skillTitle = findViewById(R.id.et_skill_title);
        skillDescription = findViewById(R.id.et_skill_description);
        skillCredits = findViewById(R.id.et_skill_credits);
        categorySpinner = findViewById(R.id.spinner_category);
        barterSwitch = findViewById(R.id.switch_barter);
        skillImage = findViewById(R.id.iv_skill_image);
        postButton = findViewById(R.id.btn_post_skill);

        // Populate Category Spinner
        String[] categories = {"Select Category", "Design", "Coding", "Writing", "Media", "Business", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        categorySpinner.setAdapter(adapter);

        // Image Upload Click Listener
        skillImage.setOnClickListener(v -> Toast.makeText(PostSkillActivity.this, "Upload Image Clicked", Toast.LENGTH_SHORT).show());

        // Post Skill Button Click
        postButton.setOnClickListener(v -> {
            if (validateFields()) {
                Toast.makeText(PostSkillActivity.this, "Skill Posted Successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    // Validate Fields
    private boolean validateFields() {
        return !skillTitle.getText().toString().trim().isEmpty() &&
                !skillDescription.getText().toString().trim().isEmpty() &&
                !categorySpinner.getSelectedItem().toString().equals("Select Category");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
