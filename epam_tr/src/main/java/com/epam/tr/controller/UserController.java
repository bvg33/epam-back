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

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody UserDto userDto) {
        service.create(userDto);
        return new ResponseEntity(CREATED);
    }

    @GetMapping
    public ResponseEntity getAll() {
        List<UserDto> userDtoList = service.getAll();
        return ResponseEntity.status(OK).body(userDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable int id) {
        UserDto user = service.getById(id);
        return ResponseEntity.status(OK).body(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity update(@PathVariable int userId, @Valid @RequestBody UserDto newAppUser) {
        service.update(userId, newAppUser);
        return new ResponseEntity(OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity delete(@PathVariable int userId) {
        service.delete(userId);
        return new ResponseEntity(NO_CONTENT);
    }
}
