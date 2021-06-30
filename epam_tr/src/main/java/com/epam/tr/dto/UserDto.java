package com.epam.tr.dto;

import com.epam.tr.entities.AppUser;
import com.epam.tr.entities.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDto {

    private int id;
    private String login;
    @JsonIgnore
    private String password;
    private UserRole userRole;

    public UserDto(int id, String login, String password, UserRole userRole) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.userRole = userRole;
    }

    public static UserDto from(AppUser user) {
        int id = user.getId();
        String login = user.getLogin();
        String password = user.getPassword();
        UserRole userRole = user.getUserRole();
        return new UserDto(id,login,password,userRole);
    }

    public AppUser to(){
        int id = this.id;
        String login = this.login;
        String password = this.password;
        UserRole userRole = this.userRole;
        return new AppUser(id,login,password,userRole);
    }

    public int getId() {
        return id;
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
