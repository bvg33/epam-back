package com.epam.tr.security.jwt;

import com.epam.tr.security.request.RequestProcessor;
import com.epam.tr.service.SecurityUserDetailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JWTFilter extends GenericFilterBean {

    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private SecurityUserDetailService service;
    @Autowired
    private RequestProcessor processor;

    private static final Logger LOGGER = Logger.getLogger(JWTFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        String token = processor.getTokenFromRequest((HttpServletRequest) request);
        if (!token.isEmpty() && jwtProvider.validateToken(token)) {
            String userLogin = processor.getLoginFromToken(token);
            UserDetails userDetails = service.loadUserByUsername(userLogin);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        try {
            chain.doFilter(request, response);
        } catch (IOException | ServletException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
