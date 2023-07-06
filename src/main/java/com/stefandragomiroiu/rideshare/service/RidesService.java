package com.stefandragomiroiu.rideshare.service;

import com.stefandragomiroiu.rideshare.controller.dto.request.RideAndConnections;
import com.stefandragomiroiu.rideshare.controller.dto.response.DriverAndAverageRatings;
import com.stefandragomiroiu.rideshare.controller.dto.response.RideWithLocationsAndDriver;
import com.stefandragomiroiu.rideshare.repository.LocationRepository;
import com.stefandragomiroiu.rideshare.repository.RideConnectionsRepository;
import com.stefandragomiroiu.rideshare.repository.RidesRepository;
import com.stefandragomiroiu.rideshare.repository.UserRepository;
import com.stefandragomiroiu.rideshare.repository.dto.DriverRating;
import com.stefandragomiroiu.rideshare.repository.dto.RideConnectionWithAvailableSeats;
import com.stefandragomiroiu.rideshare.repository.dto.RideWithDepartureArrivalTimes;
import com.stefandragomiroiu.rideshare.tables.pojos.Locations;
import com.stefandragomiroiu.rideshare.tables.pojos.RideConnections;
import com.stefandragomiroiu.rideshare.tables.pojos.Rides;
import com.stefandragomiroiu.rideshare.tables.pojos.Users;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RidesService {

    private final RideConnectionsRepository rideConnectionsRepository;
    private final RidesRepository ridesRepository;
    private final UserRepository userRepository;

    private final LocationRepository locationRepository;

    public RidesService(RideConnectionsRepository rideConnectionsRepository, RidesRepository ridesRepository, UserRepository userRepository, LocationRepository locationRepository) {
        this.rideConnectionsRepository = rideConnectionsRepository;
        this.ridesRepository = ridesRepository;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
    }

    public List<RideWithLocationsAndDriver> findBy(Long departure, Long arrival, LocalDate date, Integer seats) {
        Locations departureLocation = locationRepository.findOptionalById(departure)
                .orElseThrow(() -> new IllegalArgumentException("Invalid departure location"));
        Locations arrivalLocation = locationRepository.findOptionalById(arrival)
                .orElseThrow(() -> new IllegalArgumentException("Invalid arrival location"));

        List<RideWithLocationsAndDriver> result = new ArrayList<>();

        List<RideWithDepartureArrivalTimes> rides = ridesRepository.findBy(departure, arrival, date);
        for (var ride: rides) {
            var rideWithLocationsAndDriver = new RideWithLocationsAndDriver();

            rideWithLocationsAndDriver.setRideId(ride.getRideId());
            rideWithLocationsAndDriver.setDepartureTime(ride.getDepartureTime());
            rideWithLocationsAndDriver.setArrivalTime(ride.getArrivalTime());
            rideWithLocationsAndDriver.setDepartureLocation(departureLocation);
            rideWithLocationsAndDriver.setArrivalLocation(arrivalLocation);

            // Set driver information to the dto
            Users driver = userRepository.findById(ride.getDriver());
            DriverRating driverRating = userRepository.findAverageRatingBy(ride.getDriver());
            rideWithLocationsAndDriver.setDriverAndAverageRatings(
                    new DriverAndAverageRatings(
                            driver.getUserId(),
                            driver.getFirstName() + driver.getLastName(),
                            driverRating.getAverageRating(),
                            driverRating.getNumberOfReviews()
                    )
            );

            // Check if all connections have available seats and set total price
            List<RideConnectionWithAvailableSeats> rideConnections = rideConnectionsRepository.findBy(
                    ride.getDepartureTime(),
                    ride.getArrivalTime(),
                    ride.getRideId()
            );
            boolean availableSeats = rideConnections.stream()
                    .allMatch(rideConnection -> rideConnection.getAvailableSeats() > seats);
            if (availableSeats) {
                int totalPrice = rideConnections.stream()
                        .mapToInt(RideConnectionWithAvailableSeats::getPrice)
                        .sum();
                rideWithLocationsAndDriver.setPrice(totalPrice);

                // If all connections have available seats add it to the result set
                result.add(rideWithLocationsAndDriver);
            }
        }
        return result;
    }

    @Transactional
    public void create(RideAndConnections rideAndConnections) {
        Rides rides = new Rides(
                null,
                rideAndConnections.driver(),
                rideAndConnections.seats(),
                rideAndConnections.additionalComment(),
                null
        );
        ridesRepository.insert(rides);
        for (RideConnections rideConnection : rideAndConnections.connections()) {
            rideConnection.setRideId(rides.getRideId());
            rideConnectionsRepository.insert(rideConnection);
        }
    }
}
