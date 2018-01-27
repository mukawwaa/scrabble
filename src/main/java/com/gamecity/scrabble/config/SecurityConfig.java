package com.gamecity.scrabble.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.gamecity.scrabble.service.impl.LoginProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private LoginProvider authenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManager)
    {
        authenticationManager.authenticationProvider(authenticationProvider);
        // credentials do not deleted because rest service need authenticated user password
        authenticationManager.eraseCredentials(false);
    }

    @Override
    public void configure(WebSecurity web)
    {
        // ignore spring security to access resources folder
        web.ignoring().antMatchers("/resources/**");
        web.ignoring().antMatchers("/pages/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/menu")
                .failureUrl("/login?error")
        .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
        .and()
            .authorizeRequests()
                .antMatchers("/rest/**").authenticated()
                .antMatchers("/v/**").authenticated()
                .antMatchers("/menu").authenticated()
        .and()
            .exceptionHandling()
                .accessDeniedPage("/403")
        .and()
            .csrf()
                .disable();
    }
}
