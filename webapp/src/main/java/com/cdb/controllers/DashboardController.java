package com.cdb.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.cdb.model.dto.DashboardDto;
import com.cdb.model.dto.PageDto;
import com.cdb.services.Impl.GestionOrdinateur;
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
    GestionOrdinateur gestionOrdinateur;

    /**
     * Gets the gestion ordinateur.
     *
     * @return the gestion ordinateur
     */
    public GestionOrdinateur getGestionOrdinateur() {

        return gestionOrdinateur;

    }

    /**
     * Instantiates a new dashboard controller.
     */
    public DashboardController() {

        LOGGER.info("DashboardController instancié");

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
        recuperationModelAffichageDashboard(request, model);
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

        try {

            LOGGER.info("DashboardController: POST");
            gestionOrdinateur.suppressionOrdinateur(DashboardDtoMapper
                    .recoveryListDeleteRequestPost(request));

        } catch (DataAccessException e) {

            model.addAttribute("error",
                    "Erreur lors de la suppression des ordinateurs");

        }

        recuperationModelAffichageDashboard(request, model);
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
    private void recuperationModelAffichageDashboard(HttpServletRequest request,
            Model model) {

        try {

            DashboardDto dashboard = DashboardDtoMapper
                    .recoveryDashboardRequestGet(request);
            PageDto page = null;
            page = gestionOrdinateur.findOrdinateurByPage(
                    dashboard.getNumPage(), dashboard.getRowByPage(),
                    dashboard.getFilter(), dashboard.getSort(),
                    dashboard.getDesc());
            model.addAttribute("page", page);
            request.getSession().setAttribute("page", page);

        } catch (DataAccessException e) {

            model.addAttribute("error",
                    "Erreur lors du chargement des ordinateurs");

        }

    }

}
