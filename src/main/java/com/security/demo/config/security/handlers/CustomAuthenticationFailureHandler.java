package com.security.demo.config.security.handlers;

import com.security.demo.config.security.SpringSecurityConfig;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;


/**
 *
 * @author Sachin
 *
 * plugined via {@link SpringSecurityConfig#configure(AuthenticationManagerBuilder)#onAuthenticationFailure(HttpServletRequest, HttpServletResponse, AuthenticationException)}
 *
 * second varient is via redirection to page. We are expanding to handler for scaling,logging.
 *
 * */
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

        String jsonPayload = " invalid login credentials \n {\"message\" : \"%s\", \"timestamp\" : \"%s\" }";
        httpServletResponse.getOutputStream().println(String.format(jsonPayload, e.getMessage(), Calendar.getInstance().getTime()));
    }
}