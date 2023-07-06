package com.stefandragomiroiu.rideshare.controller;

import com.stefandragomiroiu.rideshare.controller.dto.request.RideAndConnections;
import com.stefandragomiroiu.rideshare.controller.dto.response.RideWithLocationsAndDriver;
import com.stefandragomiroiu.rideshare.controller.exception.ResourceNotFoundException;
import com.stefandragomiroiu.rideshare.service.RidesService;
import com.stefandragomiroiu.rideshare.tables.daos.UsersDao;
import com.stefandragomiroiu.rideshare.tables.pojos.Users;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/rides")
public class RidesController {
    private final UsersDao usersDao;
    private final RidesService ridesService;

    public RidesController(UsersDao usersDao, RidesService ridesService) {
        this.usersDao = usersDao;
        this.ridesService = ridesService;
    }

    @GetMapping("/findBy")
    public List<RideWithLocationsAndDriver> findBy(
            @RequestParam Long departure,
            @RequestParam Long arrival,
            @RequestParam LocalDate date,
            @RequestParam Integer seats
            ) {
        return ridesService.findBy(departure, arrival, date, seats);
    }

    @GetMapping("/{id}")
    public Users findById(@PathVariable long id) {
        return usersDao.findOptionalById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody RideAndConnections rideAndConnections) {
        ridesService.create(rideAndConnections);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable long id, @RequestBody Users user) {
        if (usersDao.findOptionalById(id).isEmpty()) {
            throw new ResourceNotFoundException();
        }
        usersDao.update(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {
        if (usersDao.findOptionalById(id).isEmpty()) {
            throw new ResourceNotFoundException();
        }
        usersDao.deleteById(id);
    }
}
