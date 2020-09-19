package com.example.gradetracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for showing the courses on the ScheduleActivity
 * @author Albert
 * @author Ben
 * @author Ozzie
 * @version 1.0
 */
public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassHolder> {
    List<Course> courses = new ArrayList<>();
    private OnCourseListener mOnCourseListener;

    public ClassAdapter(List<Course> courses, OnCourseListener onCourseListener){
        this.courses = courses;
        this.mOnCourseListener = onCourseListener;
    }
    @Override
    public ClassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.class_card, parent, false);
        return new ClassHolder(itemView, mOnCourseListener);
    }
    //set the cards to their respective info
    //TODO: still need to pull the grades, right now just using the courseID to fill in
    @Override
    public void onBindViewHolder(@NonNull ClassHolder holder, int position) {
        Course currentCourse = courses.get(position);
        holder.textViewTitle.setText(currentCourse.getTitle());
        holder.textViewDescription.setText(currentCourse.getDescription());
        holder.textViewPriority.setText(String.valueOf(currentCourse.getCourseGrade()));
    }

    @Override
    public int getItemCount() {
        return courses == null ? 0 : courses.size();
    }

    public void setCourses(List<Course> courses){
        this.courses = courses;
        notifyDataSetChanged();
    }

    class ClassHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;
        OnCourseListener onCourseListener;

        public ClassHolder(View itemView, OnCourseListener onCourseListener){
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
            this.onCourseListener = onCourseListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCourseListener.onCourseClick(getAdapterPosition());
        }
    }

    public interface OnCourseListener{
        void onCourseClick(int position);
    }
}
