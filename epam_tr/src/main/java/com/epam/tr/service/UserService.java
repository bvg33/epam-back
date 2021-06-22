package com.epam.tr.service;

import com.epam.tr.entities.AppUser;

import java.util.List;

public interface UserService extends Service<AppUser> {
    List<AppUser> getAllUsers();

    AppUser getUserById(int id);
}
