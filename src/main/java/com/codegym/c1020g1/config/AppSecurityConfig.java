package com.codegym.c1020g1.config;

import com.codegym.c1020g1.service.account.IUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    IUserAccountService accountService;

    @Autowired
    private CustomizeSuccessHandle customizeSuccessHandle;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService((UserDetailsService) accountService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .authorizeRequests().antMatchers("/category").hasRole("USER")
//                .and()
//                .authorizeRequests().antMatchers("/products").hasRole("ADMIN")
//                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/products").hasAnyRole("USER", "ADMIN").
                and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/products/create").hasAnyRole("ADMIN").
                and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/products/edit/{id}").hasRole("ADMIN").
                and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/products/delete/{id}").hasRole("ADMIN").
                and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/products/search").hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin().successHandler(customizeSuccessHandle).loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        http.csrf().disable();
    }
}
