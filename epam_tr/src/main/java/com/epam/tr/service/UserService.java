package com.epam.tr.service;

import com.epam.tr.dto.UserDto;
import com.epam.tr.entities.AppUser;
import com.epam.tr.exceptions.InvalidCredentialsException;
import com.epam.tr.exceptions.InvalidFileException;

import java.io.IOException;
import java.util.List;

public interface UserService{
    List<UserDto> getAll();

    UserDto getById(int id);

    void create(UserDto user);

    void update(int oldUserId, UserDto newEntity);

    void delete(int userId);
}
