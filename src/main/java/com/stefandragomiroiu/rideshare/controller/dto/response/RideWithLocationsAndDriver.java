package com.stefandragomiroiu.rideshare.controller.dto.response;

import com.stefandragomiroiu.rideshare.jooq.tables.pojos.Location;

import java.time.LocalDateTime;

public class RideWithLocationsAndDriver {
    private Long rideId;
    private Location departureLocation;
    private Location arrivalLocation;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer price;
    private DriverAndAverageRatings driverAndAverageRatings;

    public RideWithLocationsAndDriver() {}

    public RideWithLocationsAndDriver(Long rideId, Location departureLocation, Location arrivalLocation, LocalDateTime departureTime, LocalDateTime arrivalTime, Integer price, DriverAndAverageRatings driverAndAverageRatings) {
        this.rideId = rideId;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.driverAndAverageRatings = driverAndAverageRatings;
    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    public Location getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(Location departureLocation) {
        this.departureLocation = departureLocation;
    }

    public Location getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(Location arrivalLocation) {
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

    public DriverAndAverageRatings getDriverAndAverageRatings() {
        return driverAndAverageRatings;
    }

    public void setDriverAndAverageRatings(DriverAndAverageRatings driverAndAverageRatings) {
        this.driverAndAverageRatings = driverAndAverageRatings;
    }
}
