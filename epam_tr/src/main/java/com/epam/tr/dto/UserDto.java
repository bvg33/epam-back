package com.epam.tr.dto;

import com.epam.tr.entities.AppUser;
import com.epam.tr.entities.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDto {

    private String login;
    @JsonIgnore
    private String password;
    private UserRole userRole = UserRole.USER;

    public UserDto(String login, String password, UserRole userRole) {
        this.login = login;
        this.password = password;
        this.userRole = userRole;
    }

    public UserDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static UserDto from(AppUser user) {
        String login = user.getLogin();
        String password = user.getPassword();
        UserRole userRole = user.getUserRole();
        return new UserDto(login, password, userRole);
    }

    public AppUser to() {
        String login = this.login;
        String password = this.password;
        UserRole userRole = this.userRole;
        return new AppUser(login, password, userRole);
    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getUserRole() {
        return userRole;
    }
}
