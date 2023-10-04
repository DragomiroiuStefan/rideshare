package com.stefandragomiroiu.rideshare.controller.dto.request;

public record Booking(
        Long userId,
        Long rideId,
        Long departure,
        Long arrival,
        Integer adults,
        Integer children
) {
}
