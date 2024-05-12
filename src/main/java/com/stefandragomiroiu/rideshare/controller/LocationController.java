package com.stefandragomiroiu.rideshare.controller;

import com.stefandragomiroiu.rideshare.controller.exception.ResourceNotFoundException;
import com.stefandragomiroiu.rideshare.jooq.tables.pojos.Location;
import com.stefandragomiroiu.rideshare.repository.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/locations")
class LocationController {

    private static final Logger logger = LoggerFactory.getLogger(LocationController.class);

    public static final String LOCATION_NOT_FOUND_ERROR_MESSAGE = "Location with ID %d not found";

    private final LocationRepository locationRepository;

    LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @GetMapping
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @GetMapping("/{locationId}")
    public Location findById(@PathVariable Long locationId) {
        return locationRepository.findOptionalById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(LOCATION_NOT_FOUND_ERROR_MESSAGE, locationId)
                ));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location create(@RequestBody Location location) {
        logger.info("Create request for location: {}", location);
        locationRepository.insert(location);
        return location;
    }

    @PutMapping("/{locationID}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long locationID, @RequestBody Location location) {
        logger.info("Update request for location: {}", location);
        if (locationRepository.findOptionalById(locationID).isEmpty()) {
            String errorMessage = String.format(LOCATION_NOT_FOUND_ERROR_MESSAGE, locationID);
            throw new ResourceNotFoundException(errorMessage);
        }
        locationRepository.update(location);
    }

    @DeleteMapping("/{locationID}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long locationID) {
        logger.info("Delete request location with ID: {}", locationID);
        locationRepository.deleteById(locationID);
    }
}
