package com.epam.tr.dto;

import com.epam.tr.entities.AppUser;
import com.epam.tr.entities.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {

    @NotNull
    @Size(min=3,max=16)
    private String login;
    @JsonIgnore
    @NotNull
    @Size(min=4,max=16)
    private String password;
    @Size(min=4,max=5)
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

    public AppUser toAppUser() {
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
