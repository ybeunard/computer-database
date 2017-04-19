package com.cdb.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

// TODO: Auto-generated Javadoc
/**
 * The Interface InterfaceUserService.
 */
public interface InterfaceUserService {

    /**
     * Load user by username.
     *
     * @param username
     *            the username
     * @return the user details
     * @throws UsernameNotFoundException
     *             the username not found exception
     */
    UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException;

}
