package com.epam.tr.controller;

import com.epam.tr.entities.AppUser;
import com.epam.tr.entities.Token;
import com.epam.tr.exceptions.InvalidCredentialsException;
import com.epam.tr.security.jwt.JWTProvider;
import com.epam.tr.service.SecurityUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private SecurityUserDetailService userDetailService;
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private BCryptPasswordEncoder encoder;
    private static final String INVALID_CREDENTIALS = "Invalid Credentials";

    @PostMapping
    public ResponseEntity<Token> auth(@RequestBody AppUser user) throws InvalidCredentialsException, UsernameNotFoundException {
        UserDetails userDetails = userDetailService.loadUserByUsername(user.getLogin());
        String password = user.getPassword();
        String hashPassword = userDetails.getPassword();
        boolean isPasswordMatches = encoder.matches(password, hashPassword);
        if (isPasswordMatches) {
            String token = jwtProvider.generateToken(userDetails.getUsername());
            return ResponseEntity.status(HttpStatus.OK).body(new Token(token));
        } else {
            throw new InvalidCredentialsException(INVALID_CREDENTIALS);
        }
    }
}
