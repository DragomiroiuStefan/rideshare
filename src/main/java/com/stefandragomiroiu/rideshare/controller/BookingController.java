package com.stefandragomiroiu.rideshare.controller;

import com.stefandragomiroiu.rideshare.controller.dto.request.Booking;
import com.stefandragomiroiu.rideshare.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Booking booking) {
        bookingService.create(booking);
    }
}
