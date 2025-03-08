package com.example.skillx;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActiviy extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;

    private ImageView profileImage;
    private EditText userName, userEmail, userPhone, userBio, userSkills;
    private Button saveChangesBtn, logoutBtn;
    private Uri imageUri; // Store the selected image URI

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activiy);

        // Initialize Views
        profileImage = findViewById(R.id.profile_image);
        userName = findViewById(R.id.user_name);
        userEmail = findViewById(R.id.user_email);
        userPhone = findViewById(R.id.user_phone);
        userBio = findViewById(R.id.user_bio);
        userSkills = findViewById(R.id.user_skills);
        saveChangesBtn = findViewById(R.id.save_changes_btn);
        logoutBtn = findViewById(R.id.logout_btn);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("SkillXPrefs", MODE_PRIVATE);

        // Load User Data
        loadUserData();

        // Profile Image Click to Change
        profileImage.setOnClickListener(v -> selectImage());

        // Save Changes Button Click
        saveChangesBtn.setOnClickListener(v -> saveUserData());

        // Logout Button Click
        logoutBtn.setOnClickListener(v -> logoutUser());
    }

    private void loadUserData() {
        userName.setText(sharedPreferences.getString("userName", "John Doe"));
        userEmail.setText(sharedPreferences.getString("userEmail", "johndoe@example.com"));
        userPhone.setText(sharedPreferences.getString("userPhone", "+91 9876543210"));
        userBio.setText(sharedPreferences.getString("userBio", "Android Developer | UI/UX Enthusiast"));
        userSkills.setText(sharedPreferences.getString("userSkills", "Java, Kotlin, UI/UX, Firebase"));
    }

    private void saveUserData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName", userName.getText().toString().trim());
        editor.putString("userEmail", userEmail.getText().toString().trim());
        editor.putString("userPhone", userPhone.getText().toString().trim());
        editor.putString("userBio", userBio.getText().toString().trim());
        editor.putString("userSkills", userSkills.getText().toString().trim());
        editor.apply();

        Toast.makeText(this, "Profile Updated!", Toast.LENGTH_SHORT).show();
    }

    private void logoutUser() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.apply();

        Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ProfileActiviy.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void selectImage() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.getData();
            profileImage.setImageURI(imageUri);
        }
    }
}
