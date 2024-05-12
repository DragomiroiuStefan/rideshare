package com.stefandragomiroiu.rideshare.controller.dto.request;

public record Location(
        Long locationId,
        String city,
        String county
) {
}
