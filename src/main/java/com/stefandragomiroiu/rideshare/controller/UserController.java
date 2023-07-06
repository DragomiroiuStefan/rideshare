package com.stefandragomiroiu.rideshare.controller;

import com.stefandragomiroiu.rideshare.controller.exception.ResourceNotFoundException;
import com.stefandragomiroiu.rideshare.tables.daos.UserDao;
import com.stefandragomiroiu.rideshare.tables.pojos.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserDao usersDao;

    public UserController(UserDao userDao) {
        this.usersDao = userDao;
    }

    @GetMapping
    public List<User> findAll() {
        return usersDao.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable long id) {
        return usersDao.findOptionalById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody User user) {
        usersDao.insert(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable long id, @RequestBody User user) {
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
