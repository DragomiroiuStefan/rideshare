package com.stefandragomiroiu.rideshare.controller;

import com.stefandragomiroiu.rideshare.controller.exception.ResourceNotFoundException;
import com.stefandragomiroiu.rideshare.tables.daos.UsersDao;
import com.stefandragomiroiu.rideshare.tables.pojos.Users;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/users")
public class UserController {
    private final UsersDao usersDao;

    public UserController(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @GetMapping
    public List<Users> findAll() {
        return usersDao.findAll();
    }

    @GetMapping("/{id}")
    public Users findById(@PathVariable long id) {
        return usersDao.findOptionalById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Users user) {
        usersDao.insert(user);
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
