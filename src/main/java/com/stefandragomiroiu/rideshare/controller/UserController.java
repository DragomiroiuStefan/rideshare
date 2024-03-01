package com.stefandragomiroiu.rideshare.controller;

import com.stefandragomiroiu.rideshare.config.security.EmailAndPassword;
import com.stefandragomiroiu.rideshare.controller.exception.BadRequestException;
import com.stefandragomiroiu.rideshare.controller.exception.EmailALreadyUsedException;
import com.stefandragomiroiu.rideshare.controller.exception.InvalidCredentialsException;
import com.stefandragomiroiu.rideshare.controller.exception.ResourceNotFoundException;
import com.stefandragomiroiu.rideshare.jooq.enums.Role;
import com.stefandragomiroiu.rideshare.jooq.tables.pojos.User;
import com.stefandragomiroiu.rideshare.repository.UserRepository;
import com.stefandragomiroiu.rideshare.service.AuthService;
import com.stefandragomiroiu.rideshare.util.FileUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin()
@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public static final String USER_NOT_FOUND_ERROR_MESSAGE = "User %s not found";
    public static final String USER_ALREADY_EXISTS_ERROR_MESSAGE = "User %s already exists";

    @Value("${user.upload.dir}")
    private String userUploadDir;

    private final UserRepository userRepository;
    private final AuthService authService;

    public UserController(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @GetMapping
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable Long userId) {
        return userRepository.findOptionalById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(USER_NOT_FOUND_ERROR_MESSAGE, userId)
                ));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody User user) {
        userRepository.insert(user);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProfileInfo(@PathVariable long userId, @RequestBody User user, HttpServletResponse response) {
        if (userRepository.findOptionalById(userId).isEmpty()) {
            throw new ResourceNotFoundException(String.format(USER_NOT_FOUND_ERROR_MESSAGE, userId));
        }

        userRepository.updateProfileInfo(user);
        User savedUser = userRepository.findById(userId);

        String jwt = authService.createJWT(savedUser);
        response.addHeader(HttpHeaders.AUTHORIZATION, jwt);
        response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long userId) {
        if (userRepository.findOptionalById(userId).isEmpty()) {
            throw new ResourceNotFoundException(String.format(USER_NOT_FOUND_ERROR_MESSAGE, userId));
        }
        userRepository.deleteById(userId);
    }

    @PostMapping("/login")
    public User login(@RequestBody EmailAndPassword emailAndPassword, HttpServletResponse response) {
        Optional<User> optionalUser = userRepository.fetchOptionalByEmail(emailAndPassword.email());
        if (optionalUser.isEmpty() || !emailAndPassword.password().equals(optionalUser.get().getPassword())) {
            throw new InvalidCredentialsException("Login Failed: Your email or password is incorrect");
        }
        User user = optionalUser.get();
        String jwt = authService.createJWT(user);
        response.addHeader(HttpHeaders.AUTHORIZATION, jwt);
        response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION);

        return user;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody User user, HttpServletResponse response) {
        if (userRepository.fetchOptionalByEmail(user.getEmail()).isPresent()) {
            throw new EmailALreadyUsedException("Register failed: Email " + user.getEmail() + " already in use");
        }
        user.setRole(Role.USER);
        userRepository.insert(user);

        String jwt = authService.createJWT(user);
        response.addHeader(HttpHeaders.AUTHORIZATION, jwt);
        response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION);

        return user;
    }

    @PostMapping("/uploadProfilePicture")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestParam("profilePicture") MultipartFile profilePicture) throws IOException {
        if (profilePicture == null || profilePicture.getOriginalFilename() == null) {
            throw new BadRequestException("Missing upload file");
        }

        String fileExtension = FileUtil.getFileExtension(profilePicture.getOriginalFilename())
                .orElseThrow(() -> new BadRequestException("invalid File Extension"));
        String fileName = "profile-picture." + fileExtension;
        var userId = 1L; //TODO
        String uploadDir = userUploadDir + userId;

        FileUtil.saveFile(uploadDir, fileName, profilePicture);
        userRepository.setProfilePicture(userId, fileName);

        logger.info("User {} uploaded profile picture {} to {}", userId, fileName, uploadDir);
    }
}
