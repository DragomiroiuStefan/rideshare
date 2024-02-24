package com.stefandragomiroiu.rideshare.controller;

import com.stefandragomiroiu.rideshare.controller.exception.ResourceAlreadyExistsException;
import com.stefandragomiroiu.rideshare.controller.exception.ResourceNotFoundException;
import com.stefandragomiroiu.rideshare.controller.validators.RequestValidator;
import com.stefandragomiroiu.rideshare.repository.UserRepository;
import com.stefandragomiroiu.rideshare.repository.VehicleRepository;
import com.stefandragomiroiu.rideshare.jooq.tables.pojos.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/vehicles")
class VehicleController {

    private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);

    public static final String USER_NOT_FOUND_ERROR_MESSAGE = "User %s not found";
    public static final String VEHICLE_NOT_FOUND_ERROR_MESSAGE = "Vehicle %s not found";
    public static final String VEHICLE_ALREADY_EXISTS_ERROR_MESSAGE = "Vehicle %s already exists";

    private final RequestValidator<Vehicle> vehicleValidator;
    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;


    VehicleController(RequestValidator<Vehicle> vehicleValidator, VehicleRepository vehicleRepository, UserRepository userRepository) {
        this.vehicleValidator = vehicleValidator;
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{plateNumber}")
    public Vehicle findById(@PathVariable String plateNumber) {
        return vehicleRepository.findOptionalById(plateNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(VEHICLE_NOT_FOUND_ERROR_MESSAGE, plateNumber)
                ));
    }

    @GetMapping
    public List<Vehicle> findByOwner(@RequestParam Long userId) {
        if (userRepository.findOptionalById(userId).isEmpty()) {
            String errorMessage = String.format(USER_NOT_FOUND_ERROR_MESSAGE, userId);
            throw new ResourceNotFoundException(errorMessage);
        }
        return vehicleRepository.fetchByOwner(userId);
    }

    /**
     * {@code POST  /vehicle} : Create a new vehicle.
     * @param vehicle to create
     * @return {@code 201 (Created)} with created vehicle in response body or {@code 409 (conflict)} if vehicle id already exists or {@code 404 (Not Found)} if vehicle owner doesn't exist
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicle create(@RequestBody Vehicle vehicle) {
        logger.info("Create vehicle request: {}", vehicle);
        vehicleValidator.validate(vehicle);
        if (vehicleRepository.findOptionalById(vehicle.getPlateNumber()).isPresent()) {
            String errorMessage = String.format(VEHICLE_ALREADY_EXISTS_ERROR_MESSAGE, vehicle.getPlateNumber());
            throw new ResourceAlreadyExistsException(errorMessage);
        }
        if (userRepository.findOptionalById(vehicle.getOwner()).isEmpty()) {
            String errorMessage = String.format(USER_NOT_FOUND_ERROR_MESSAGE, vehicle.getOwner());
            throw new ResourceNotFoundException(errorMessage);
        }
        vehicleRepository.insert(vehicle);
        return vehicle;
    }

    @PutMapping("/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String plateNumber, @RequestBody Vehicle vehicle) {
        logger.info("Update vehicle request: {}", vehicle);
        if (vehicleRepository.findOptionalById(plateNumber).isEmpty()) {
            String errorMessage = String.format(VEHICLE_NOT_FOUND_ERROR_MESSAGE, plateNumber);
            throw new ResourceNotFoundException(errorMessage);
        }
        vehicleRepository.update(vehicle);
    }

    @DeleteMapping("/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String plateNumber) {
        logger.info("Delete vehicle request: {}", plateNumber);
        if (vehicleRepository.findOptionalById(plateNumber).isEmpty()) {
            String errorMessage = String.format(VEHICLE_NOT_FOUND_ERROR_MESSAGE, plateNumber);
            throw new ResourceNotFoundException(errorMessage);
        }
        vehicleRepository.deleteById(plateNumber);
    }
}
