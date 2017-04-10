package com.cdb.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface InterfaceUserService {

    UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException;
    
}
