package com.stefandragomiroiu.rideshare.controller;

import com.stefandragomiroiu.rideshare.config.security.EmailAndPassword;
import com.stefandragomiroiu.rideshare.controller.exception.*;
import com.stefandragomiroiu.rideshare.enums.Role;
import com.stefandragomiroiu.rideshare.repository.UserRepository;
import com.stefandragomiroiu.rideshare.service.AuthService;
import com.stefandragomiroiu.rideshare.tables.pojos.User;
import com.stefandragomiroiu.rideshare.util.FileUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
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
        String fileName = StringUtils.cleanPath(profilePicture.getOriginalFilename());
        var userId = 1L;
        String uploadDir = "public/user-upload/" + userId;

        FileUtil.saveFile(uploadDir, fileName, profilePicture);
//        userRepository.setProfilePicture(userId, fileName);

        logger.info("User {} uploaded profile picture {} to {}", userId, fileName, uploadDir);
    }
}
