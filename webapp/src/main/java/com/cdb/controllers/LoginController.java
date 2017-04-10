package com.cdb.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login.htm")
public class LoginController {

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(LoginController.class);
    
    /**
     * Instantiates a new dashboard controller.
     */
    public LoginController() {

        LOGGER.info("LoginController Instantiated");

    }
    
}
