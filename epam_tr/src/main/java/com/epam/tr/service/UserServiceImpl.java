package com.epam.tr.service;

import com.epam.tr.dao.Dao;
import com.epam.tr.dto.UserDto;
import com.epam.tr.entities.AppUser;
import com.epam.tr.entities.UserRole;
import com.epam.tr.exceptions.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Dao<AppUser> dao;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public void create(UserDto userDto) throws InvalidCredentialsException {
        String login = userDto.getLogin();
        String password = userDto.getPassword();
        String encodePassword = encoder.encode(password);
        AppUser newAppUser = new AppUser(login, encodePassword);
        dao.insert(newAppUser);
    }

    @Override
    public void update(int oldUserId, UserDto newEntity) {
        String login = newEntity.getLogin();
        String password = newEntity.getPassword();
        UserRole role = newEntity.getUserRole();
        dao.update(new AppUser(oldUserId, login, password, role));
    }

    @Override
    public void delete(int userId) {
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
