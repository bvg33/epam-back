package com.epam.tr.service;

import com.epam.tr.dao.Dao;
import com.epam.tr.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Collections.singletonList;

@Component
public class SecurityUserDetailService implements UserDetailsService {

    private static final String USER_NOT_FOUND = "User not found";
    private Dao<AppUser> dao;

    @Autowired
    public SecurityUserDetailService(Dao<AppUser> dao) {
        this.dao = dao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = dao.getUserByNickname(username);
        if (appUser == null) {
            throw new UsernameNotFoundException(USER_NOT_FOUND);
        }
        List<SimpleGrantedAuthority> authorities = singletonList(new SimpleGrantedAuthority("ROLE_" + appUser.getUserRole().toString()));
        String login = appUser.getLogin();
        String password = appUser.getPassword();
        return new User(login, password, authorities);
    }
}