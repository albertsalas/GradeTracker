package com.example.gradetracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryHolder> {
    List<GradeCategory> categories = new ArrayList<>();
    private OnCategoryListener mOnCategoryListener;

    public CategoriesAdapter(List<Course> courses, OnCourseListener onCourseListener){
        this.categories = courses;
        this.mOnCategoryListener = onCourseListener;
    }
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.class_card, parent, false);
        return new CategoryHolder(itemView, mOnCategoryListener);
    }
    //set the cards to their respective info
    //TODO: still need to pull the grades, right now just using the courseID to fill in
    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        GradeCategory currentCategory = categories.get(position);
        holder.textViewTitle.setText(currentCourse.getTitle());
        holder.textViewDescription.setText(currentCourse.getDescription());
        holder.textViewPriority.setText(String.valueOf(currentCourse.getCourseID()));
    }

    @Override
    public int getItemCount() {
        return categories == null ? 0 : categories.size();
    }

    public void setCourses(List<GradeCategory> categories){
        this.categories = categories;
        notifyDataSetChanged();
    }

    class CategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;
        OnCategoryListener onCategoryListener;

        public CategoryHolder(View itemView, OnCategoryListener onCourseListener){
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
            this.onCategoryListener = onCourseListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCategoryListener.onCategoryClick(getAdapterPosition());
        }
    }

    public interface OnCategoryListener{
        void onCourseClick(int position);
    }
}
