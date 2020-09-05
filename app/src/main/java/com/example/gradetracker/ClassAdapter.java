package com.example.gradetracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassHolder> {
    private List<Class> classes = new ArrayList<>();
    @NonNull
    @Override
    public ClassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.class_card, parent, false);
        return new ClassHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassHolder holder, int position) {
        Class currentClass = classes.get(position);
        holder.textViewTitle.setText(currentClass.getClass());
        holder.textViewDescription.setText(currentClass.getDescription());
        holder.textViewPrioritiy.setText(currentClass.getGrade());
    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    public void setClasses(List<Classes> classes){
        this.classes = classes;
        notifyDataSetChanged();
    }

    class ClassHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPrioritiy;

        public ClassHolder(View itemView){
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewPrioritiy = itemView.findViewById(R.id.text_view_priority);
        }
    }
}
