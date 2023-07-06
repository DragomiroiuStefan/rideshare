package com.stefandragomiroiu.rideshare.controller.dto.response;

public class DriverAndAverageRatings {
    private Long userId;
    private String fullName;
    private Double averageRating;
    private Integer numberOfRatings;

    public DriverAndAverageRatings() {
    }

    public DriverAndAverageRatings(Long userId, String fullName, Double averageRating, Integer numberOfRatings) {
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

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(Integer numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }
}
