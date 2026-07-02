package com.example.kaamsetuog;

import java.io.Serializable;
import java.util.List;

public class Worker implements Serializable {
    public String name;
    public String work;
    public String phone;
    public String experience;
    public String rating;
    public String jobsCount;
    public String status;
    public boolean isVerified;
    public String reviewerName;
    public String reviewText;
    public List<String> tags;
    public String hiredBySummary;
    public List<String> hiredByFullList;
    public int image;

    // Original constructor for HomeFragment (updated to match field names)
    public Worker(String name, String rating, String experience, int image, String phone) {
        this.name = name;
        this.rating = rating;
        this.experience = experience;
        this.image = image;
        this.phone = phone;
    }

    // New constructor for Word of Mouth
    public Worker(String name, String work, String phone, String experience, String rating, 
                  String jobsCount, String status, boolean isVerified, String reviewerName, 
                  String reviewText, List<String> tags, String hiredBySummary, List<String> hiredByFullList) {
        this.name = name;
        this.work = work;
        this.phone = phone;
        this.experience = experience;
        this.rating = rating;
        this.jobsCount = jobsCount;
        this.status = status;
        this.isVerified = isVerified;
        this.reviewerName = reviewerName;
        this.reviewText = reviewText;
        this.tags = tags;
        this.hiredBySummary = hiredBySummary;
        this.hiredByFullList = hiredByFullList;
    }

    // Comprehensive constructor
    public Worker(String name, String work, String phone, String experience, String rating,
                  String jobsCount, String status, boolean isVerified, String reviewerName,
                  String reviewText, List<String> tags, String hiredBySummary,
                  List<String> hiredByFullList, int image) {
        this.name = name;
        this.work = work;
        this.phone = phone;
        this.experience = experience;
        this.rating = rating;
        this.jobsCount = jobsCount;
        this.status = status;
        this.isVerified = isVerified;
        this.reviewerName = reviewerName;
        this.reviewText = reviewText;
        this.tags = tags;
        this.hiredBySummary = hiredBySummary;
        this.hiredByFullList = hiredByFullList;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getWork() {
        return work;
    }

    public String getPhone() {
        return phone;
    }

    public String getExperience() {
        return experience;
    }

    public String getRating() {
        return rating;
    }

    public String getJobsCount() {
        return jobsCount;
    }

    public String getStatus() {
        return status;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public String getReviewText() {
        return reviewText;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getHiredBySummary() {
        return hiredBySummary;
    }

    public List<String> getHiredByFullList() {
        return hiredByFullList;
    }

    public int getImage() {
        return image;
    }
}