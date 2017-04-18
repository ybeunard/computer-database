package com.cdb.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cdb.services.Impl.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  public static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

  @Autowired
  @Qualifier("userService")
  UserService userService;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    LOGGER.info("SECURITY           2");
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    LOGGER.info("SECURITY           1");
    http.authorizeRequests().antMatchers( "/addComputer.html","/editComputer.html")
    .access("hasRole('ROLE_ADMIN')").and().formLogin()
    .loginPage("/login.html").failureUrl("/login.html?error")
    .usernameParameter("username")
    .passwordParameter("password")
    .and().logout().logoutSuccessUrl("/login.html?logout")
    .and().csrf()
    .and().exceptionHandling().accessDeniedPage("/403");  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    return encoder;
  }

}
