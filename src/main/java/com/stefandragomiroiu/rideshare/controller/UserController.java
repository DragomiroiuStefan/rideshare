package com.stefandragomiroiu.rideshare.controller;

import com.stefandragomiroiu.rideshare.controller.exception.ResourceNotFoundException;
import com.stefandragomiroiu.rideshare.repository.UserRepository;
import com.stefandragomiroiu.rideshare.tables.pojos.User;
import com.stefandragomiroiu.rideshare.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable long id) {
        return userRepository.findOptionalById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody User user) {
        userRepository.insert(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable long id, @RequestBody User user) {
        if (userRepository.findOptionalById(id).isEmpty()) {
            throw new ResourceNotFoundException();
        }
        userRepository.update(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {
        if (userRepository.findOptionalById(id).isEmpty()) {
            throw new ResourceNotFoundException();
        }
        userRepository.deleteById(id);
    }

    @PostMapping("/uploadProfilePicture")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestParam("profilePicture") MultipartFile profilePicture) throws IOException {
        String fileName = StringUtils.cleanPath(profilePicture.getOriginalFilename());
        var userId = 1L;
        String uploadDir = "public/user-upload/" + userId;

        FileUtil.saveFile(uploadDir, fileName, profilePicture);
//        userRepository.setProfilePicture(userId, fileName);

        logger.info("User {} uploaded profile picture {} to {}", userId, fileName, uploadDir);
    }
}
