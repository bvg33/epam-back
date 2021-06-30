package com.epam.tr.controller;

import com.epam.tr.dto.UserDto;
import com.epam.tr.entities.AppUser;
import com.epam.tr.exceptions.InvalidCredentialsException;
import com.epam.tr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity create(@RequestBody UserDto userDto) {
        String login = userDto.getLogin();
        String password = userDto.getPassword();
        String encodePassword = encoder.encode(password);
        AppUser newAppUser = new AppUser(login, encodePassword);
        service.create(newAppUser);
        return new ResponseEntity(CREATED);
    }

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.status(OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable int id) {
        UserDto user = service.getById(id);
        return ResponseEntity.status(OK).body(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity update(@PathVariable int userId, @RequestBody UserDto newAppUser) {
        service.update(userId, newAppUser);
        return new ResponseEntity(CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity delete(@PathVariable int userId) {
        service.delete(userId);
        return new ResponseEntity(NO_CONTENT);
    }
}
