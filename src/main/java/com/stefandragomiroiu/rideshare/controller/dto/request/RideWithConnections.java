package com.stefandragomiroiu.rideshare.controller.dto.request;

import com.stefandragomiroiu.rideshare.tables.pojos.RideConnection;

import java.time.LocalDateTime;
import java.util.List;

public record RideWithConnections(
        Long rideId,
        Long driver,
        String vehicle,
        Integer seats,
        String additionalComment,
        LocalDateTime postedAt,
        List<RideConnection> connections
        ) {
}
