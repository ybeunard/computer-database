package com.cdb.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.cdb.model.dto.PageDto;
import com.cdb.services.Impl.ComputerService;
import com.cdb.utils.mappers.PageDtoMapper;

/**
 * The Class DashboardController.
 */
@Controller
@RequestMapping("/dashboard.html")
@SessionAttributes("currentPage")
public class DashboardController {

    /**
     * The error / information message.
     */
    private String message = "";

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(DashboardController.class);

    /** The gestion ordinateur. */
    @Autowired
    private ComputerService computerService;

    /**
     * Gets the gestion ordinateur.
     *
     * @return the gestion ordinateur
     */
    public ComputerService getComputerService() {

        return computerService;

    }

    /**
     * Gets the current page.
     *
     * @return the current page
     */
    @ModelAttribute("currentPage")
    public PageDto getCurrentPage() {

        return new PageDto();

    }

    /**
     * Dashboard get.
     *
     * @param parameters
     *            the parameters
     * @param currentPage
     *            the current page
     * @return the model and view
     */
    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView dashboardGet(
            @RequestParam MultiValueMap<String, String> parameters,
            @ModelAttribute("currentPage") PageDto currentPage) {

        LOGGER.info("DashboardController: GET");
        ModelAndView model = new ModelAndView("dashboard");
        recoveryDisplayDashboard(parameters, model, currentPage);
        return model;

    }

    /**
     * Dashboard post.
     *
     * @param parameters
     *            the parameters
     * @param currentPage
     *            the current page
     * @return the model and view
     */
    @RequestMapping(method = RequestMethod.POST)
    protected ModelAndView dashboardPostModel(
            @RequestParam MultiValueMap<String, String> parameters,
            @ModelAttribute("currentPage") PageDto currentPage) {

        LOGGER.info("DashboardController: POST");
        ModelAndView model = new ModelAndView("dashboard");
        computerService.deleteComputer(
                PageDtoMapper.recoveryListDeleteRequestPost(parameters));
        recoveryDisplayDashboard(parameters, model, currentPage);
        return model;

    }

    /**
     * Recuperation model affichage add computer.
     *
     * @param parameters
     *            the parameters
     * @param model
     *            the model
     * @param currentPage
     *            the current page
     */
    private void recoveryDisplayDashboard(
            MultiValueMap<String, String> parameters, ModelAndView model,
            PageDto currentPage) {

        PageDto page = PageDtoMapper.recoveryPageDtoRequestGet(parameters,
                currentPage);
        try {
            page = computerService.findComputerByPage(page);
            model.addObject("currentPage", page);
        } catch (RuntimeException exception) {
            message = exception.getMessage();
        } finally {
            model.addObject("message", message);
            message = "";
            Object principal = SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();

            if (principal instanceof UserDetails) {

                String username = ((UserDetails) principal).getUsername();
                if (!username.equals("anonymousUser")) {

                    model.addObject("username", username);

                }

            }
        }

    }

}
