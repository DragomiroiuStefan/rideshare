package com.stefandragomiroiu.rideshare.controller.dto.request;

import com.stefandragomiroiu.rideshare.tables.pojos.RideConnection;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record PublishRideDto(
        Long driver,
        LocalDate departureDate,
        Integer seats,
        String additionalComment,
        String vehicle,
        String status,
        List<RideConnection> connections
        ) {
}
