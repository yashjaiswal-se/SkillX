package com.example.skillx;

public class Skill {
    private String title;
    private String category;
    private int cost;
    private String description;
    private boolean isAvailable;
    private String imageUrl;  // 🖼 Image URL for Skill
    private String userName;  // 👤 User who posted the skill
    private float userRating; // ⭐ User's rating

    // ✅ Default Constructor (Needed for Firebase & JSON Parsing)
    public Skill() {
    }

    public Skill(String title, String category, int cost, String description, boolean isAvailable, String imageUrl, String userName, float userRating) {
        this.title = title;
        this.category = category;
        this.cost = cost;
        this.description = description;
        this.isAvailable = isAvailable;
        this.imageUrl = (imageUrl == null || imageUrl.trim().isEmpty())
                ? "android.resource://com.example.skillx/drawable/sample_skill"
                : imageUrl;
        this.userName = userName;
        this.userRating = userRating;
    }

    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public int getCost() { return cost; }
    public String getDescription() { return description; }
    public boolean isAvailable() { return isAvailable; }

    // 🖼 Prevents Null Image URL Issues
    public String getImageUrl() {
        return (imageUrl == null || imageUrl.trim().isEmpty())
                ? "android.resource://com.example.skillx/drawable/sample_skill"
                : imageUrl;
    }

    // 👤 Get User Name (Handle Null)
    public String getUserName() {
        return userName != null ? userName : "Unknown User";
    }

    // ⭐ Prevents Negative Ratings
    public float getUserRating() {
        return Math.max(userRating, 0.0f);
    }
}
