package com.stefandragomiroiu.rideshare.repository.projection;

public class DriverRating {
    private Double averageRating;
    private Integer numberOfReviews;

    public DriverRating() {
    }

    public DriverRating(Double averageRating, Integer numberOfReviews) {
        this.averageRating = averageRating;
        this.numberOfReviews = numberOfReviews;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(Integer numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }
}
