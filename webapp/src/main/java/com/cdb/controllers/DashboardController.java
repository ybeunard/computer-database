package com.cdb.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.cdb.model.dto.PageDto;
import com.cdb.services.Impl.ComputerService;
import com.cdb.utils.mappers.PageDtoMapper;

/**
 * The Class DashboardController.
 */
@Controller
@RequestMapping("/dashboard.htm")
public class DashboardController {

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(DashboardController.class);

    /** The gestion ordinateur. */
    @Autowired
    ComputerService computerService;
    
    /**
     * Gets the gestion ordinateur.
     *
     * @return the gestion ordinateur
     */
    public ComputerService getComputerService() {

        return computerService;

    }

    /**
     * Dashboard get.
     *
     * @param request
     *            the request
     * @param model
     *            the model
     * @return the model and view
     */
    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView dashboardGet(@RequestParam MultiValueMap<String, String> parameters) {

        LOGGER.info("DashboardController: GET");
        ModelAndView model = new ModelAndView("dashboard");
        recoveryDisplayDashboard(parameters, model);
        return model;

    }

    /**
     * Dashboard post.
     *
     * @param request
     *            the request
     * @param model
     *            the model
     * @return the model and view
     */
    @RequestMapping(method = RequestMethod.POST)
    protected ModelAndView dashboardPostModel(@RequestParam MultiValueMap<String, String> parameters) {

        LOGGER.info("DashboardController: POST");
        ModelAndView model = new ModelAndView("dashboard");
        computerService.deleteComputer(PageDtoMapper
                .recoveryListDeleteRequestPost(parameters));
        recoveryDisplayDashboard(parameters, model);
        return new ModelAndView("dashboard");

    }

    /**
     * Recuperation model affichage add computer.
     *
     * @param request
     *            the request
     * @param model
     *            the model
     */
    private void recoveryDisplayDashboard(MultiValueMap<String, String> parameters,
            ModelAndView model) {

            PageDto page = PageDtoMapper.recoveryPageDtoRequestGet(parameters);
            page = computerService.findComputerByPage(page);
            model.addObject("numPage", page.getNumPage());
            model.addObject("rowByPage", page.getRowByPage());
            model.addObject("search", page.getFilter());
            model.addObject("sort", page.getSort());
            model.addObject("nbComputer", page.getNbComputer());
            model.addObject("computers", page.getContent());

    }

}
