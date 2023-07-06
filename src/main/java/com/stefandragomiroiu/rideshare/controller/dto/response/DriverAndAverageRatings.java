package com.stefandragomiroiu.rideshare.controller.dto.response;

public class DriverAndAverageRatings {
    private Long userId;
    private String fullName;
    private double averageRating;
    private int numberOfRatings;

    public DriverAndAverageRatings() {
    }

    public DriverAndAverageRatings(Long userId, String fullName, double averageRating, int numberOfRatings) {
        this.userId = userId;
        this.fullName = fullName;
        this.averageRating = averageRating;
        this.numberOfRatings = numberOfRatings;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }
}
