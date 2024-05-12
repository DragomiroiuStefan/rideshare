package com.stefandragomiroiu.rideshare.repository.projection;

import java.time.LocalDateTime;

public class RideWithDepartureArrivalTimes {
    private Long rideId;
    private Integer seats;
    private Long driver;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public RideWithDepartureArrivalTimes() {
    }

    public RideWithDepartureArrivalTimes(Long rideId, Integer seats, Long driver, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.rideId = rideId;
        this.seats = seats;
        this.driver = driver;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Long getDriver() {
        return driver;
    }

    public void setDriver(Long driver) {
        this.driver = driver;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
