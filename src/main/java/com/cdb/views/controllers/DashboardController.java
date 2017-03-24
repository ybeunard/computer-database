package com.cdb.views.controllers;

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

@Controller
@RequestMapping("/dashboard.htm")
public class DashboardController {

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(DashboardController.class);

    @Autowired
    GestionOrdinateur gestionOrdinateur;

    public GestionOrdinateur getGestionOrdinateur() {

        return gestionOrdinateur;

    }

    public DashboardController() {

        LOGGER.info("DashboardController instancié");

    }

    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView dashboardGet(HttpServletRequest request,
            Model model) {

        LOGGER.info("DashboardController: GET");
        recuperationModelAffichageAddComputer(request, model);
        return new ModelAndView("dashboard");

    }

    @RequestMapping(method = RequestMethod.POST)
    protected ModelAndView dashboardPost(HttpServletRequest request,
            Model model) {

        try {

            LOGGER.info("DashboardController: POST");
            gestionOrdinateur.suppressionOrdinateur(DashboardDtoMapper
                    .recuperationListSuppresionRequestPost(request));

        } catch (DataAccessException e) {

            model.addAttribute("error",
                    "Erreur lors de la suppression des ordinateurs");

        }

        recuperationModelAffichageAddComputer(request, model);
        return new ModelAndView("dashboard");

    }

    private void recuperationModelAffichageAddComputer(
            HttpServletRequest request, Model model) {

        try {

            DashboardDto dashboard = DashboardDtoMapper
                    .recuperationDashboardRequestGet(request);
            PageDto page = null;
            page = gestionOrdinateur.findOrdinateurByPage(
                    dashboard.getNumPage(), dashboard.getNbParPage(),
                    dashboard.getFiltre(), dashboard.getTrie(),
                    dashboard.getDesc());
            model.addAttribute("page", page);
            request.getSession().setAttribute("page", page);

        } catch (DataAccessException e) {

            model.addAttribute("error",
                    "Erreur lors du chargement des ordinateurs");

        }

    }

}
