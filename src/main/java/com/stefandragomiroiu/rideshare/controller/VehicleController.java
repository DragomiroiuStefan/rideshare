package com.stefandragomiroiu.rideshare.controller;

import com.stefandragomiroiu.rideshare.controller.exception.BadRequestException;
import com.stefandragomiroiu.rideshare.controller.exception.ResourceAlreadyExistsException;
import com.stefandragomiroiu.rideshare.controller.exception.ResourceNotFoundException;
import com.stefandragomiroiu.rideshare.repository.UserRepository;
import com.stefandragomiroiu.rideshare.repository.VehicleRepository;
import com.stefandragomiroiu.rideshare.tables.pojos.Vehicle;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/vehicles")
class VehicleController {

    public static final String INVALID_USER_ERROR_MESSAGE = "Invalid user Id %s";
    public static final String VEHICLE_NOT_FOUND_ERROR_MESSAGE = "Vehicle %s not found";
    public static final String VEHICLE_ALREADY_EXISTS_ERROR_MESSAGE = "Vehicle %s already exists";

    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;


    VehicleController(VehicleRepository vehicleRepository, UserRepository userRepository) {
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Vehicle> findByOwner(@RequestParam Long userId) {
        if (userRepository.findOptionalById(userId).isEmpty()) {
            String errorMessage = String.format(INVALID_USER_ERROR_MESSAGE, userId);
            throw new BadRequestException(errorMessage);
        }
        return vehicleRepository.fetchByOwner(userId);
    }

    @GetMapping("/{plateNumber}")
    public Vehicle findById(@PathVariable String plateNumber) {
        String errorMessage = String.format(VEHICLE_NOT_FOUND_ERROR_MESSAGE, plateNumber);
        return vehicleRepository.findOptionalById(plateNumber)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicle create(@RequestBody Vehicle vehicle) {
        if (vehicleRepository.findOptionalById(vehicle.getPlateNumber()).isPresent()) {
            String errorMessage = String.format(VEHICLE_ALREADY_EXISTS_ERROR_MESSAGE, vehicle.getPlateNumber());
            throw new ResourceAlreadyExistsException(errorMessage);
        }
        vehicleRepository.insert(vehicle);
        return vehicle;
    }

    @PutMapping("/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String plateNumber, @RequestBody Vehicle vehicle) {
        if (vehicleRepository.findOptionalById(plateNumber).isEmpty()) {
            String errorMessage = String.format(VEHICLE_NOT_FOUND_ERROR_MESSAGE, plateNumber);
            throw new ResourceNotFoundException(errorMessage);
        }
        vehicleRepository.update(vehicle);
    }

    @DeleteMapping("/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String plateNumber) {
        if (vehicleRepository.findOptionalById(plateNumber).isEmpty()) {
            String errorMessage = String.format(VEHICLE_NOT_FOUND_ERROR_MESSAGE, plateNumber);
            throw new ResourceNotFoundException(errorMessage);
        }
        vehicleRepository.deleteById(plateNumber);
    }
}
