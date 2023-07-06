package com.stefandragomiroiu.rideshare.controller;

import com.stefandragomiroiu.rideshare.controller.dto.request.RideWithConnections;
import com.stefandragomiroiu.rideshare.controller.dto.response.RideWithLocationsAndDriver;
import com.stefandragomiroiu.rideshare.service.RideService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/rides")
public class RidesController {
    private final RideService rideService;

    public RidesController(RideService rideService) {
        this.rideService = rideService;
    }

    @GetMapping("/findBy")
    public List<RideWithLocationsAndDriver> findBy(
            @RequestParam Long departure,
            @RequestParam Long arrival,
            @RequestParam LocalDate date,
            @RequestParam Integer seats
    ) {
        return rideService.findBy(departure, arrival, date, seats);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody RideWithConnections rideWithConnections) {
        rideService.create(rideWithConnections);
    }

}
