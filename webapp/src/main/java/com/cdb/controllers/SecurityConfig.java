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

// TODO: Auto-generated Javadoc
/**
 * The Class SecurityConfig.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  /** The Constant LOGGER. */
  public static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

  /** The user service. */
  @Autowired
  @Qualifier("userService")
  UserService userService;

  /**
   * Configure global.
   *
   * @param auth the auth
   * @throws Exception the exception
   */
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
  }

  /* (non-Javadoc)
   * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/addComputer.html", "/editComputer.html").access("hasRole('ROLE_ADMIN')")
    .and().authorizeRequests().antMatchers(HttpMethod.POST, "/dashboard.html").access("hasRole('ROLE_ADMIN')")
    .and().formLogin().loginPage("/login.html").failureUrl("/login.html").usernameParameter("username").passwordParameter("password")
    .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/login.html?logout"))
    .logoutSuccessUrl("/dashboard.html")
    .and().csrf()
    .and().exceptionHandling().accessDeniedPage("/403.html");
  }

  /**
   * Password encoder.
   *
   * @return the password encoder
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    return encoder;
  }

}
