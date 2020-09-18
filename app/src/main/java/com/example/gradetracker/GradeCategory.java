package com.example.gradetracker;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.TypeConverters.DateTypeConverter;

import java.util.Date;

/**
 * GradeCategory is a class used for the categories table
 * @author Albert
 * @author Ben
 * @author Ozzie
 * @version 1.0
 */
@Entity(tableName = AppDatabase.GRADE_CATEGORY_TABLE)
@TypeConverters({DateTypeConverter.class})
public class GradeCategory implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int categoryID;
    private String title;
    private double weight;
    private int gradeID;
    private String assignedDate;

    /**
     * Constructor for making a new category
     * @param title is the title of the category, example would be "Exams"
     * @param weight is what all the assignments will be weighted against
     * @param gradeID is the ID associated with the grade table
     * @param assignedDate is the date that the category was assigned
     */
    public GradeCategory(String title, double weight, int gradeID, String assignedDate) {
        this.title = title;
        this.weight = weight;
        this.gradeID = gradeID;
        this.assignedDate = assignedDate;
    }

    /**
     * Methods that are only for the class to be parcelable
     */
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

    /**
     * Method for returning categoryID
     * @return categoryID
     */
    public int getCategoryID() {
        return categoryID;
    }

    /**
     * Method for setting categoryID;
     * @param categoryID is the new categoryID we want
     */
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    /**
     * Method for getting title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Method for setting category title
     * @param title is the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Method for getting the category weight
     * @return weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Method for setting weight
     * @param weight is the new weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Method for getting gradeID
     * @return gradeID
     */
    public int getGradeID() {
        return gradeID;
    }

    /**
     * Method for setting gradeID;
     * @param gradeID new gradeID;
     */
    public void setGradeID(int gradeID) {
        this.gradeID = gradeID;
    }

    /**
     * Method for getting assigned date
     * @return assignedDate
     */
    public String getAssignedDate() {
        return assignedDate;
    }

    /**
     * Method for setting new date
     * @param assignedDate is the new date
     */
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
