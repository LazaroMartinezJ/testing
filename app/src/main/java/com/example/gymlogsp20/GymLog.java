package com.example.gymlogsp20;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.gymlogsp20.db.AppDatabase;

import java.util.Date;

@Entity(tableName = AppDatabase.GYMLOG_TABLE)
public class GymLog {

    @PrimaryKey(autoGenerate = true)
    private int mLogId;
    private String mExercise;
    private int mReps;
    private double mWeight;
    private Date mDate;

    public GymLog(String exercise, int reps, double weight) {
        mExercise = exercise;
        mReps = reps;
        mWeight = weight;

        mDate = new Date();
    }

    public int getLogId() {
        return mLogId;
    }

    public void setLogId(int mLogId) {
        this.mLogId = mLogId;
    }

    public String getExercise() {
        return mExercise;
    }

    public void setExercise(String mExercise) {
        this.mExercise = mExercise;
    }

    public int getReps() {
        return mReps;
    }

    public void setReps(int mReps) {
        this.mReps = mReps;
    }

    public double getWeight() {
        return mWeight;
    }

    public void setWeight(double mWeight) {
        this.mWeight = mWeight;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    @Override
    public String toString() {
        String output;
        output = mExercise + " " + mWeight + " : " + mReps;
        output += "\n";
        output += getDate();
        return output;
    }
}
