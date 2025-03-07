package com.example.skillx;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.SkillViewHolder> {

    private final List<Skill> skillList;

    public SkillAdapter(List<Skill> skillList) {
        this.skillList = skillList;
    }

    @NonNull
    @Override
    public SkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_skill, parent, false);
        return new SkillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillViewHolder holder, int position) {
        Skill skill = skillList.get(position);
        holder.skillTitle.setText(skill.getTitle());
        holder.skillCategory.setText(skill.getCategory());
        holder.skillCost.setText(skill.getCost() + " Credits");

        // Add card click listener
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Clicked on: " + skill.getTitle(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return skillList.size();
    }

    public static class SkillViewHolder extends RecyclerView.ViewHolder {
        TextView skillTitle, skillCategory, skillCost;

        public SkillViewHolder(@NonNull View itemView) {
            super(itemView);
            skillTitle = itemView.findViewById(R.id.skill_title);
            skillCategory = itemView.findViewById(R.id.skill_category);
            skillCost = itemView.findViewById(R.id.skill_cost);
        }
    }
}
