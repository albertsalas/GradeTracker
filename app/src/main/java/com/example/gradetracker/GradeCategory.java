package com.example.gradetracker;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.TypeConverters.DateTypeConverter;

import java.util.Date;

@Entity(tableName = AppDatabase.GRADE_CATEGORY_TABLE)
@TypeConverters({DateTypeConverter.class})

public class GradeCategory implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int categoryID;
    private String title;
    private double weight;
    private int gradeID;
    private String assignedDate;

    public GradeCategory(String title, double weight, int gradeID, String assignedDate) {
        this.title = title;
        this.weight = weight;
        this.gradeID = gradeID;
        this.assignedDate = assignedDate;
    }

    protected GradeCategory(Parcel in) {
        categoryID = in.readInt();
        title = in.readString();
        weight = in.readDouble();
        gradeID = in.readInt();
        assignedDate = in.readString();
    }

    public static final Creator<GradeCategory> CREATOR = new Creator<GradeCategory>() {
        @Override
        public GradeCategory createFromParcel(Parcel in) {
            return new GradeCategory(in);
        }

        @Override
        public GradeCategory[] newArray(int size) {
            return new GradeCategory[size];
        }
    };

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getGradeID() {
        return gradeID;
    }

    public void setGradeID(int gradeID) {
        this.gradeID = gradeID;
    }

    public String getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(String assignedDate) {
        this.assignedDate = assignedDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(categoryID);
        dest.writeString(title);
        dest.writeDouble(weight);
        dest.writeInt(gradeID);
        dest.writeString(assignedDate);
    }
}
