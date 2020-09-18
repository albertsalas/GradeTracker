package com.example.gradetracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for showing the categories for each assignment
 * @author Albert
 * @author Ben
 * @author Ozzie
 * @version 1.0
 */
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryHolder> {
    List<GradeCategory> categories = new ArrayList<>();
    private OnCategoryListener mOnCategoryListener;

    public CategoriesAdapter(List<GradeCategory> categories, OnCategoryListener onCategoryListener){
        this.categories = categories;
        this.mOnCategoryListener = onCategoryListener;
    }
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.class_card, parent, false);
        return new CategoryHolder(itemView, mOnCategoryListener);
    }
    //set the cards to their respective info
    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        GradeCategory currentCategory = categories.get(position);
        holder.textViewTitle.setText(currentCategory.getTitle());
        holder.textViewPriority.setText(Double.toString(currentCategory.getWeight()));
    }

    @Override
    public int getItemCount() {
        return categories == null ? 0 : categories.size();
    }

    public void setCategories(List<GradeCategory> categories){
        this.categories = categories;
        notifyDataSetChanged();
    }

    class CategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewTitle;
        private TextView textViewPriority;
        OnCategoryListener onCategoryListener;

        public CategoryHolder(View itemView, OnCategoryListener onCategoryListener){
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
            this.onCategoryListener = onCategoryListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCategoryListener.onCategoryClick(getAdapterPosition());
        }
    }

    public interface OnCategoryListener{
        void onCategoryClick(int position);
    }
}
