package com.gamecity.scrabble.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.gamecity.scrabble.Resources;
import com.gamecity.scrabble.model.UserLogin;
import com.gamecity.scrabble.model.api.User;
import com.gamecity.scrabble.service.RestService;

@Component
public class LoginProvider implements AuthenticationProvider
{
    @Autowired
    private RestService restService;

    public LoginProvider()
    {
        super();
    }

    @Override
    public Authentication authenticate(Authentication authentication)
    {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
        {
            throw new BadCredentialsException("Invalid username or password!");
        }

        UserLogin login = new UserLogin(username, password);
        try
        {
            User user = restService.getSingleEntityWithAuth(Resources.UserResource.loadByUsername, login, User.class, username);
            return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
        }
        catch (Exception e)
        {
            throw new BadCredentialsException("Invalid username or password!");
        }
    }

    @Override
    public boolean supports(final Class<?> authentication)
    {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
