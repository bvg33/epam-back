package com.epam.tr.service;

import com.epam.tr.dto.UserDto;
import com.epam.tr.entities.AppUser;
import com.epam.tr.exceptions.InvalidCredentialsException;
import com.epam.tr.exceptions.InvalidFileException;

import java.io.IOException;
import java.util.List;

public interface UserService{
    List<UserDto> getAllUsers();

    UserDto getUserById(int id);

    void create(AppUser Entity) throws InvalidFileException, InvalidCredentialsException, IOException;

    void update(int oldUserId, UserDto newEntity) throws InvalidFileException, InvalidCredentialsException;

    void delete(int userId) throws InvalidFileException, InvalidCredentialsException;
}
