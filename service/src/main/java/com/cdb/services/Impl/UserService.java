package com.cdb.services.Impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.cdb.dao.Impl.UserDao;
import com.cdb.model.entities.UserRole;
import com.cdb.services.InterfaceUserService;

/**
 * The Class UserService.
 */
public class UserService implements UserDetailsService, InterfaceUserService {

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(UserService.class);

    /** The user dao. */
    @Autowired
    private UserDao userDao;

    /**
     * load user by username.
     *
     * @param username
     *            the username
     * @return userdetails
     */
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        com.cdb.model.entities.User user = userDao.findByUserName(username);

        List<GrantedAuthority> authorities = buildUserAuthority(
                user.getUserRole());

        User user2 = buildUserForAuthentication(user, authorities);
        LOGGER.info(user2.getPassword());
        return buildUserForAuthentication(user, authorities);
    }

    /**
     * Builds the user for authentication.
     *
     * @param user
     *            the user
     * @param authorities
     *            the authorities
     * @return the user
     */
    private User buildUserForAuthentication(com.cdb.model.entities.User user,
            List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }

    /**
     * Builds the user authority.
     *
     * @param userRoles
     *            the user roles
     * @return the list
     */
    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        for (UserRole userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(
                setAuths);

        return result;
    }

}
