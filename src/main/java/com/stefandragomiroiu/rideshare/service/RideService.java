package com.stefandragomiroiu.rideshare.service;

import com.stefandragomiroiu.rideshare.controller.dto.request.RideWithConnections;
import com.stefandragomiroiu.rideshare.controller.dto.response.DriverAndAverageRatings;
import com.stefandragomiroiu.rideshare.controller.dto.response.RideWithLocationsAndDriver;
import com.stefandragomiroiu.rideshare.repository.LocationRepository;
import com.stefandragomiroiu.rideshare.repository.RideConnectionsRepository;
import com.stefandragomiroiu.rideshare.repository.RideRepository;
import com.stefandragomiroiu.rideshare.repository.UserRepository;
import com.stefandragomiroiu.rideshare.repository.dto.DriverRating;
import com.stefandragomiroiu.rideshare.repository.dto.RideConnectionWithAvailableSeatsAndPrice;
import com.stefandragomiroiu.rideshare.repository.dto.RideWithDepartureArrivalTimes;
import com.stefandragomiroiu.rideshare.tables.pojos.Location;
import com.stefandragomiroiu.rideshare.tables.pojos.Ride;
import com.stefandragomiroiu.rideshare.tables.pojos.RideConnection;
import com.stefandragomiroiu.rideshare.tables.pojos.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RideService {

    private final RideConnectionsRepository rideConnectionsRepository;
    private final RideRepository rideRepository;
    private final UserRepository userRepository;

    private final LocationRepository locationRepository;

    public RideService(RideConnectionsRepository rideConnectionsRepository, RideRepository rideRepository, UserRepository userRepository, LocationRepository locationRepository) {
        this.rideConnectionsRepository = rideConnectionsRepository;
        this.rideRepository = rideRepository;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
    }

    public List<RideWithLocationsAndDriver> findBy(Long departure, Long arrival, LocalDate date, Integer seats) {
        Location departureLocation = locationRepository.findOptionalById(departure)
                .orElseThrow(() -> new IllegalArgumentException("Invalid departure location"));
        Location arrivalLocation = locationRepository.findOptionalById(arrival)
                .orElseThrow(() -> new IllegalArgumentException("Invalid arrival location"));

        List<RideWithLocationsAndDriver> result = new ArrayList<>();

        // Get rides that pass through from departure to arrival
        List<RideWithDepartureArrivalTimes> rides = rideRepository.findBy(departure, arrival, date);

        for (var ride : rides) {
            // Check if all connections have available seats
            List<RideConnectionWithAvailableSeatsAndPrice> rideConnections = rideConnectionsRepository.findBy(
                    ride.getDepartureTime(),
                    ride.getArrivalTime(),
                    ride.getRideId()
            );
            boolean availableSeats = rideConnections
                    .stream()
                    .allMatch(rideConnection -> rideConnection.getAvailableSeats() > seats);

            // If all connections don't have available don't add the ride to the result set.
            if (!availableSeats) {
                continue;
            }

            var rideWithLocationsAndDriver = new RideWithLocationsAndDriver();

            rideWithLocationsAndDriver.setRideId(ride.getRideId());
            rideWithLocationsAndDriver.setDepartureTime(ride.getDepartureTime());
            rideWithLocationsAndDriver.setArrivalTime(ride.getArrivalTime());
            rideWithLocationsAndDriver.setDepartureLocation(departureLocation);
            rideWithLocationsAndDriver.setArrivalLocation(arrivalLocation);

            // Set driver information on the dto
            User driver = userRepository.findById(ride.getDriver());
            DriverRating driverRating = userRepository.findAverageRatingBy(ride.getDriver());
            rideWithLocationsAndDriver.setDriverAndAverageRatings(
                    new DriverAndAverageRatings(
                            driver.getUserId(),
                            driver.getFirstName() + driver.getLastName(),
                            driverRating.getAverageRating(),
                            driverRating.getNumberOfReviews()
                    )
            );

            // Set Total Price
            int totalPrice = rideConnections
                    .stream()
                    .mapToInt(RideConnectionWithAvailableSeatsAndPrice::getPrice)
                    .sum();
            rideWithLocationsAndDriver.setPrice(totalPrice);

            result.add(rideWithLocationsAndDriver);
        }
        return result;
    }

    @Transactional
    public Long create(RideWithConnections rideWithConnections) {
        // TODO check if ride connections come one after another (1->2, 2->5 etc)

        Ride ride = new Ride(
                null,
                rideWithConnections.driver(),
                rideWithConnections.vehicle(),
                rideWithConnections.seats(),
                rideWithConnections.additionalComment(),
                LocalDateTime.now(),
                false
        );
        rideRepository.insert(ride);

        for (RideConnection rideConnection : rideWithConnections.connections()) {
            rideConnection.setRideId(ride.getRideId());
            rideConnectionsRepository.insert(rideConnection);
        }
        return ride.getRideId();
    }
}
