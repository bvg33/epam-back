package com.epam.tr.controller;

import com.epam.tr.entities.AppUser;
import com.epam.tr.exceptions.InvalidCredentialsException;
import com.epam.tr.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl service;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/newUser")
    public void createNewUser(@RequestBody AppUser appUser) throws InvalidCredentialsException {
        String login = appUser.getLogin();
        String password = encoder.encode(appUser.getPassword());
        AppUser newAppUser = new AppUser(login, password);
        service.create(newAppUser);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(OK).body(service.getAllUsers());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity getUserById(@PathVariable int id) {
        return ResponseEntity.status(OK).body(service.getUserById(id));
    }

    @PatchMapping("/update/{userId}")
    public ResponseEntity updateUser(@PathVariable int userId, @RequestBody AppUser newAppUser) throws InvalidCredentialsException {
        AppUser oldAppUser = service.getUserById(userId);
        service.update(oldAppUser, newAppUser);
        return new ResponseEntity(CREATED);
    }

    @PatchMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable int userId) throws InvalidCredentialsException {
        AppUser appUser = service.getUserById(userId);
        service.delete(appUser);
        return new ResponseEntity(NO_CONTENT);
    }
}
