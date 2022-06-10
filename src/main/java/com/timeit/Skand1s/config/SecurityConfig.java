package com.timeit.Skand1s.config;

import com.timeit.Skand1s.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().
                disable().authorizeRequests()
                .antMatchers("/api/v1/getUserCount").permitAll()
                .antMatchers("/api/v1/getVacationDays/**").permitAll()
                .antMatchers("/api/v1/getPending").permitAll()
                .antMatchers("/api/v1/getNumberOfTasks").permitAll()
                .antMatchers("/api/v1/addSchedule/**").permitAll()
                .antMatchers("/api/v1/getAllSchedule").permitAll()
                .antMatchers("/api/v1/updateStatus/**").permitAll()
                .antMatchers("/api/v1/getVacationById/**").permitAll()
                .antMatchers("/api/v1/getVacationByUser/**").permitAll()
                .antMatchers("/api/v1/getAllPendingVacation").permitAll()
                .antMatchers("/api/v1/getUserForVacation/**").permitAll()
                .antMatchers("/api/v1/save/vacation/**").permitAll()
                .antMatchers("/api/v1/basicauth").permitAll()
                .antMatchers("/api/v1/getRoles/**").permitAll()
                .antMatchers("/api/v1/getAllTasks/**").permitAll()
                .antMatchers("/api/v1/getAllCases/**").permitAll()
                .antMatchers("/api/v1/saveCase/**").permitAll()
                .antMatchers("/api/v1/getTasks/**").permitAll()
                .antMatchers("/api/v1/task/getNumberOfTasks/**").permitAll()
                .antMatchers("/api/v1/getUsersByUserName/**").permitAll()
                .antMatchers("/api/v1/task/save/**").hasAnyAuthority("ADMIN")
                .antMatchers("/api/v1/getUsers").permitAll()
                .antMatchers("/api/v1/user/save/**").hasAnyAuthority("ADMIN")
                .antMatchers("/api/v1/task/updateTask/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }


}