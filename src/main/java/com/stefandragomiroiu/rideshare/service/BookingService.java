package com.stefandragomiroiu.rideshare.service;

import com.stefandragomiroiu.rideshare.controller.dto.request.Booking;
import com.stefandragomiroiu.rideshare.repository.BookingRepository;
import com.stefandragomiroiu.rideshare.repository.RideRepository;
import com.stefandragomiroiu.rideshare.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RideRepository rideRepository;
    private final UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository, RideRepository rideRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.rideRepository = rideRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public int create(Booking booking) {
        // Check invalid userId, rideId, departure, arrival
        // Check dep and arr are on the ride and dep is before arr
        // Check that the ride connections have the necessary seats (adults + children) available
        throw new UnsupportedOperationException();
    }
}
