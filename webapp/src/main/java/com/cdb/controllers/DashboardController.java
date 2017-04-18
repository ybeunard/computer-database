package com.cdb.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.cdb.model.dto.DashboardDto;
import com.cdb.model.dto.PageDto;
import com.cdb.services.Impl.ComputerService;
import com.cdb.utils.mappers.DashboardDtoMapper;

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
    protected ModelAndView dashboardGet(HttpServletRequest request,
            Model model) {

        LOGGER.info("DashboardController: GET");
        recoveryDisplayDashboard(request, model);
        return new ModelAndView("dashboard");

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
    protected ModelAndView dashboardPost(HttpServletRequest request,
            Model model) {

        LOGGER.info("DashboardController: POST");
            computerService.deleteComputer(DashboardDtoMapper
                    .recoveryListDeleteRequestPost(request));
        recoveryDisplayDashboard(request, model);
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
    private void recoveryDisplayDashboard(HttpServletRequest request,
            Model model) {

        DashboardDto dashboard = DashboardDtoMapper
                    .recoveryDashboardRequestGet(request);
            PageDto page = null;
            page = computerService.findComputerByPage(
                    dashboard.getNumPage(), dashboard.getRowByPage(),
                    dashboard.getFilter(), dashboard.getSort(),
                    dashboard.getDesc());
            model.addAttribute("page", page);
            request.getSession().setAttribute("page", page);

    }

}
