package com.example.gradetracker;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.TypeConverters.DateTypeConverter;


/**
 * Represents a course/class at a school
 *
 * @author Albert, Ozzie
 */
@Entity(tableName = AppDatabase.COURSE_TABLE)
@TypeConverters({DateTypeConverter.class})
public class Course implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int courseID;
    private String instructor;
    private String title;
    private String description;
    private String startDate;
    private String endDate;

    /**
     *
     * @param instructor the course's instructor
     * @param title the course title
     * @param description the course description
     * @param startDate the course start date
     * @param endDate the course end date
     */
    public Course(String instructor, String title, String description, String startDate, String endDate) {
        this.instructor = instructor;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     *
     * @param in
     */
    protected Course(Parcel in) {
        courseID = in.readInt();
        instructor = in.readString();
        title = in.readString();
        description = in.readString();
        startDate = in.readString();
        endDate = in.readString();
    }

    /**
     *
     */
    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    /**
     *
     * @return the course ID, used to store in the database
     */
    public int getCourseID() {
        return courseID;
    }

    /**
     *
     * @param courseID the new course ID
     */
    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    /**
     *
     * @return the course instructor
     */
    public String getInstructor() {
        return instructor;
    }

    /**
     *
     * @param instructor the new course instructor
     */
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    /**
     *
     * @return the course title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title the new course title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return the course description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description the new course description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return the course start date
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     *
     * @param startDate the new course start date
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     *
     * @return the date the course ends
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     *
     * @param endDate the new date which the course ends
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     *
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     *
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(courseID);
        dest.writeString(instructor);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(startDate);
        dest.writeString(endDate);
    }
}
