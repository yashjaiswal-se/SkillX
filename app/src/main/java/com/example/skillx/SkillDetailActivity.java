package com.example.skillx;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;

public class SkillDetailActivity extends AppCompatActivity {

    private TextView skillTitle, skillDescription, skillCategory, skillCost, userName, userRating, availabilityStatus;
    private ImageView skillImage, userProfile;
    private Button tradeNow, contactUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_detail);

        // Initialize UI Components
        skillTitle = findViewById(R.id.skill_title);
        skillDescription = findViewById(R.id.skill_description);
        skillCategory = findViewById(R.id.skill_category);
        skillCost = findViewById(R.id.skill_cost);
        userName = findViewById(R.id.user_name);
        userRating = findViewById(R.id.user_rating);
        availabilityStatus = findViewById(R.id.availability_status);
        skillImage = findViewById(R.id.skill_image);
        userProfile = findViewById(R.id.user_profile);
        tradeNow = findViewById(R.id.btn_trade_now);
        contactUser = findViewById(R.id.btn_contact_user);

        // Receive Data from Intent
        Intent intent = getIntent();
        if (intent != null) {
            skillTitle.setText(intent.getStringExtra("skill_title"));
            skillDescription.setText(intent.getStringExtra("skill_description"));
            skillCategory.setText("Category: " + intent.getStringExtra("skill_category"));

            // ✅ Fix skill cost retrieval
            int cost = intent.getIntExtra("skill_cost", 0);
            skillCost.setText(cost + " Credits");

            userName.setText(intent.getStringExtra("user_name"));

            // ✅ Fix user rating display
            float rating = intent.getFloatExtra("user_rating", 0.0f);
            userRating.setText(rating > 0 ? "⭐ " + rating : "No Rating");

            // ✅ Fix availability status & color
            boolean isAvailable = intent.getBooleanExtra("availability", true);
            availabilityStatus.setText(isAvailable ? "🟢 Available" : "🔴 Not Available");
            int color = ContextCompat.getColor(this, isAvailable ? R.color.green : R.color.red);
            availabilityStatus.setTextColor(color);

            // ✅ Fix Glide NullPointerException
            String imageUrl = intent.getStringExtra("image_url");
            if (imageUrl == null || imageUrl.trim().isEmpty()) {
                imageUrl = "https://via.placeholder.com/150"; // Default placeholder
            }
            Glide.with(this).load(imageUrl).into(skillImage);
        }
    }
}
