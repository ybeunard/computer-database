package com.cdb.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * The Class LoginController.
 */
@Controller
public class LoginController {

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(LoginController.class);

    /**
     * Login.
     *
     * @param error
     *            the error
     * @param logout
     *            the logout
     * @param request
     *            the request
     * @param response
     *            the response
     * @return the model and view
     */
    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("login");
        if (error != null) {
            model.addObject("error",
                    getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }

        if (logout != null) {
            model.setViewName("redirect:/dashboard.html");
            Authentication auth = SecurityContextHolder.getContext()
                    .getAuthentication();

            new SecurityContextLogoutHandler().logout(request, response, auth);

            new CookieClearingLogoutHandler(
                    AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY)
                            .logout(request, response, null);
            return model;
        }

        return model;
    }

    /**
     * Gets the error message.
     *
     * @param request
     *            the request
     * @param key
     *            the key
     * @return the error message
     */
    private String getErrorMessage(HttpServletRequest request, String key) {

        Exception exception = (Exception) request.getSession()
                .getAttribute(key);

        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "Invalid username and password!";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = "Invalid username and password!";
        }

        return error;
    }

    /**
     * Accesss denied.
     *
     * @param request
     *            the request
     * @return the model and view
     */
    @RequestMapping(value = "/403.html", method = RequestMethod.GET)
    public ModelAndView accesssDenied(HttpServletRequest request) {

        ModelAndView model = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
        }

        model.setViewName("403");
        return model;

    }

}
