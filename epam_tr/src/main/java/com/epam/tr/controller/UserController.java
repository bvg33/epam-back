package com.epam.tr.controller;

import com.epam.tr.dto.UserDto;
import com.epam.tr.entities.AppUser;
import com.epam.tr.exceptions.InvalidCredentialsException;
import com.epam.tr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping
    public void createNewUser(@RequestBody UserDto userDto) throws InvalidCredentialsException, IOException {
        String login = userDto.getLogin();
        String password = userDto.getPassword();
        String encodePassword = encoder.encode(password);
        AppUser newAppUser = new AppUser(login, encodePassword);
        service.create(newAppUser);
    }

    @GetMapping
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(OK).body(service.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable int id) {
        return ResponseEntity.status(OK).body(service.getUserById(id));
    }

    @PutMapping("/{userId}")
    public ResponseEntity updateUser(@PathVariable int userId, @RequestBody UserDto newAppUser) throws InvalidCredentialsException {
        service.update(userId, newAppUser);
        return new ResponseEntity(CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable int userId) throws InvalidCredentialsException {
        service.delete(userId);
        return new ResponseEntity(NO_CONTENT);
    }
}
