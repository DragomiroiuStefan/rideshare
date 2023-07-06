package com.stefandragomiroiu.rideshare.controller.dto.response;

import com.stefandragomiroiu.rideshare.tables.pojos.Locations;

import java.time.LocalDateTime;

public class RideWithLocationsAndDriver {
    private Long rideId;
    private DriverAndAverageRatings driverAndAverageRatings;
    private Locations departureLocation;
    private Locations arrivalLocation;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer price;

    public RideWithLocationsAndDriver() {
    }

    public RideWithLocationsAndDriver(Long rideId, DriverAndAverageRatings driverAndAverageRatings, Locations departureLocation, Locations arrivalLocation, LocalDateTime departureTime, LocalDateTime arrivalTime, Integer price) {
        this.rideId = rideId;
        this.driverAndAverageRatings = driverAndAverageRatings;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    public DriverAndAverageRatings getDriverAndAverageRatings() {
        return driverAndAverageRatings;
    }

    public void setDriverAndAverageRatings(DriverAndAverageRatings driverAndAverageRatings) {
        this.driverAndAverageRatings = driverAndAverageRatings;
    }

    public Locations getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(Locations departureLocation) {
        this.departureLocation = departureLocation;
    }

    public Locations getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(Locations arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
