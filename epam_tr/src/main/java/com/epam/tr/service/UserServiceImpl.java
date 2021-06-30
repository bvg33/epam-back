package com.epam.tr.service;

import com.epam.tr.dao.Dao;
import com.epam.tr.dto.UserDto;
import com.epam.tr.entities.AppUser;
import com.epam.tr.entities.UserRole;
import com.epam.tr.exceptions.InvalidCredentialsException;
import com.epam.tr.service.logic.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserServiceImpl implements UserService {

    private Dao<AppUser> dao;
    private Validator<AppUser> validator;

    @Autowired
    public UserServiceImpl(Dao<AppUser> dao, Validator<AppUser> validator) {
        this.dao = dao;
        this.validator = validator;
    }

    @Override
    public void create(AppUser entity) throws InvalidCredentialsException {
        validator.isValid(entity);
        dao.insert(entity);
    }

    @Override
    public void update(int oldUserId, UserDto newEntity) throws InvalidCredentialsException {
        validator.isValid(newEntity.to());
        String login = newEntity.getLogin();
        String password = newEntity.getPassword();
        UserRole role = newEntity.getUserRole();
        dao.update(new AppUser(oldUserId, login, password, role));
    }

    @Override
    public void delete(int userId) throws InvalidCredentialsException {
        AppUser entity = dao.getById(userId);
        dao.delete(entity);
    }

    @Override
    public List<UserDto> getAll() {
        List<AppUser> users = dao.getAll();
        return users.stream().map(UserDto::from).collect(toList());
    }

    @Override
    public UserDto getById(int id) {
        AppUser user = dao.getById(id);
        return UserDto.from(user);
    }
}
