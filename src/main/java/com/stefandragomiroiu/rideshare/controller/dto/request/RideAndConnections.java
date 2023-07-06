package com.stefandragomiroiu.rideshare.controller.dto.request;

import com.stefandragomiroiu.rideshare.tables.pojos.RideConnections;

import java.time.LocalDateTime;
import java.util.List;

public record RideAndConnections(
        Long rideId,
        Long driver,
        Integer seats,
        String additionalComment,
        LocalDateTime postedAt,
        List<RideConnections> connections
        ) {
}
