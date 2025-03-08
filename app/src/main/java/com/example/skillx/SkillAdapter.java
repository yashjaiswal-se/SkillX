package com.example.skillx;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import java.util.List;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.SkillViewHolder> {

    private final List<Skill> skillList;
    private final Context context;

    public SkillAdapter(Context context, List<Skill> skillList) {
        this.context = context;
        this.skillList = skillList;
    }

    @NonNull
    @Override
    public SkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_skill, parent, false);
        return new SkillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillViewHolder holder, int position) {
        Skill skill = skillList.get(position);

        holder.skillTitle.setText(skill.getTitle());
        holder.skillCategory.setText("Category: " + skill.getCategory());
        holder.skillCost.setText(skill.getCost() + " Credits");
        holder.skillDescription.setText(skill.getDescription());

        // Set availability status
        if (skill.isAvailable()) {
            holder.skillAvailability.setText("🟢 Available");
            holder.skillAvailability.setTextColor(ContextCompat.getColor(context, R.color.green));
        } else {
            holder.skillAvailability.setText("🔴 Unavailable");
            holder.skillAvailability.setTextColor(ContextCompat.getColor(context, R.color.red));
        }

        // Ensure Image URL is valid
        String imageUrl = (skill.getImageUrl() != null && !skill.getImageUrl().trim().isEmpty())
                ? skill.getImageUrl()
                : "https://via.placeholder.com/150"; // Default placeholder

        // Load image only if skillImage is not null
        if (holder.skillImage != null) {
            Glide.with(context)
                    .load(imageUrl)
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.sample_skill) // While loading
                            .error(R.drawable.sample_skill) // On failure
                            .diskCacheStrategy(DiskCacheStrategy.ALL)) // Cache for efficiency
                    .into(holder.skillImage);
        }

        // Open Skill Details Screen
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, SkillDetailActivity.class);
            intent.putExtra("skill_title", skill.getTitle());
            intent.putExtra("skill_description", skill.getDescription());
            intent.putExtra("skill_category", skill.getCategory());
            intent.putExtra("skill_cost", skill.getCost());
            intent.putExtra("user_name", skill.getUserName());
            intent.putExtra("user_rating", skill.getUserRating() > 0 ? skill.getUserRating() : "No Rating");
            intent.putExtra("availability", skill.isAvailable());
            intent.putExtra("image_url", imageUrl); // Always pass a valid image URL

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return skillList.size();
    }

    public static class SkillViewHolder extends RecyclerView.ViewHolder {
        TextView skillTitle, skillCategory, skillCost, skillDescription, skillAvailability;
        ImageView skillImage;

        public SkillViewHolder(@NonNull View itemView) {
            super(itemView);
            skillTitle = itemView.findViewById(R.id.skill_title);
            skillCategory = itemView.findViewById(R.id.skill_category);
            skillCost = itemView.findViewById(R.id.skill_cost);
            skillDescription = itemView.findViewById(R.id.skill_description);
            skillAvailability = itemView.findViewById(R.id.availability_status);
            skillImage = itemView.findViewById(R.id.skill_image);
        }
    }
}
