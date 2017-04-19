package com.cdb.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/addComputer.html", "/editComputer.html").access("hasRole('ROLE_ADMIN')").and().authorizeRequests().antMatchers(HttpMethod.POST, "/dashboard.html").access("hasRole('ROLE_ADMIN')")
    .and().formLogin().loginPage("/login.html").failureUrl("/login.html").usernameParameter("username").passwordParameter("password")
    .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/login.html?logout")).logoutSuccessUrl("/dashboard.html").deleteCookies("JSESSIONID.f78fede9", "JSESSIONID.ece89e89", "JSESSIONID.de05ba98", "JSESSIONID.7a6cfe0e", "JSESSIONID.7759a461", "JSESSIONID.3cf4f5b5", "JSESSIONID.38e07a7f", "JSESSIONID").invalidateHttpSession(true)
    .and().csrf()
    .and().exceptionHandling().accessDeniedPage("/403.html");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    return encoder;
  }

}
