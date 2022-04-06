package com.joseloya.torrexfitness.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

    @Configuration
    @EnableWebSecurity
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//        securing your web application.
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // @formatter:off
//            authorizeRequests() is where you can specify user access authorizations
             http.authorizeRequests()
                    .mvcMatchers("/login").permitAll()
//                     Order is important from here on.
//                     the order from top to bottom is: specific access to generic access.
                    .mvcMatchers("/deleteEmployee/**").hasAnyRole("SUPERADMIN")
                    .mvcMatchers("/showFormForUpdate/**").hasAnyRole("ADMIN", "SUPERADMIN")
                    .mvcMatchers("/showEmployeeForm/**").hasAnyRole("ADMIN", "SUPERADMIN")
                    .mvcMatchers("/saveEmployee/**").hasAnyRole("ADMIN", "SUPERADMIN")
                    .mvcMatchers("/**").hasAnyRole("USER", "ADMIN", "SUPERADMIN")
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                    .logout()
                    .permitAll();
            // @formatter:on
        }


//        this function essentially defines your users in an in-memory database.
//        once the application is terminated, all in-memory users are erased?
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//            this function automatically uses Bcrypt
            PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

            auth.inMemoryAuthentication()
                    .withUser("user").password(passwordEncoder.encode("user")).roles("USER").and()
                    .withUser("admin").password(passwordEncoder.encode("admin")).roles("USER", "ADMIN").and()
                    .withUser("superadmin").password(passwordEncoder.encode("superadmin")).roles("USER", "ADMIN", "SUPERADMIN");

        }
    }

