package com.stefandragomiroiu.rideshare.controller;

import com.stefandragomiroiu.rideshare.controller.dto.request.LocationDTO;
import com.stefandragomiroiu.rideshare.controller.dto.validation.Update;
import com.stefandragomiroiu.rideshare.controller.exception.ResourceNotFoundException;
import com.stefandragomiroiu.rideshare.controller.mapper.LocationMapper;
import com.stefandragomiroiu.rideshare.jooq.tables.pojos.Location;
import com.stefandragomiroiu.rideshare.repository.LocationRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/locations")
class LocationController {

    private static final Logger logger = LoggerFactory.getLogger(LocationController.class);

    public static final String LOCATION_NOT_FOUND_ERROR_MESSAGE = "Location with ID %d not found";

    private final LocationMapper locationMapper;
    private final LocationRepository locationRepository;

    LocationController(LocationMapper locationMapper, LocationRepository locationRepository) {
        this.locationMapper = locationMapper;
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
    public Location create(@Valid @RequestBody LocationDTO locationDTO) {
        logger.info("Create request for location: {}", locationDTO);
        Location location = locationMapper.toEntity(locationDTO);
        locationRepository.insert(location);
        return location;
    }

    @PutMapping("/{locationID}")
    @ResponseStatus(HttpStatus.OK)
    public Location update(@PathVariable Long locationID, @Validated(Update.class) @RequestBody LocationDTO locationDTO) {
        logger.info("Update request for location: {}", locationDTO);
        if (locationRepository.findOptionalById(locationID).isEmpty()) {
            String errorMessage = String.format(LOCATION_NOT_FOUND_ERROR_MESSAGE, locationID);
            throw new ResourceNotFoundException(errorMessage);
        }
        Location location = locationMapper.toEntity(locationDTO);
        locationRepository.update(location);
        return location;
    }

    @DeleteMapping("/{locationID}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long locationID) {
        logger.info("Delete request location with ID: {}", locationID);
        locationRepository.deleteById(locationID);
    }
}
