package com.epam.tr.service;

import com.epam.tr.dao.Dao;
import com.epam.tr.entities.AppUser;
import com.epam.tr.exceptions.InvalidCredentialsException;
import com.epam.tr.service.logic.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        checkValid(entity);
        dao.insert(entity);
    }

    private void checkValid(AppUser entity) throws InvalidCredentialsException {
        if (!validator.isValid(entity)) {
            throw new InvalidCredentialsException("Invalid login or password");
        }
    }

    @Override
    public void update(AppUser oldEntity, AppUser newEntity) throws InvalidCredentialsException {
        checkValid(oldEntity);
        checkValid(newEntity);
        dao.update(new AppUser(oldEntity.getId(), newEntity.getLogin(), newEntity.getPassword(), newEntity.getUserRole()));
    }

    @Override
    public void delete(AppUser entity) throws InvalidCredentialsException {
        checkValid(entity);
        dao.delete(entity);
    }

    @Override
    public List<AppUser> getAllUsers() {
        return dao.getAll();
    }

    @Override
    public AppUser getUserById(int id) {
        return dao.getById(id);
    }
}
