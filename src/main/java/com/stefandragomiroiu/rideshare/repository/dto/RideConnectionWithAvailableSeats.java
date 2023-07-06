package com.stefandragomiroiu.rideshare.repository.dto;

public class RideConnectionWithAvailableSeats {
    private Long connectionId;
    private Integer price;
    private Integer availableSeats;

    public RideConnectionWithAvailableSeats() {
    }

    public RideConnectionWithAvailableSeats(Long connectionId, Integer price, Integer availableSeats) {
        this.connectionId = connectionId;
        this.price = price;
        this.availableSeats = availableSeats;
    }

    public Long getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(Long connectionId) {
        this.connectionId = connectionId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }
}
