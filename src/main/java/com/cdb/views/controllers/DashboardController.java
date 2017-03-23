package com.cdb.views.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;
import com.cdb.model.dto.DashboardDto;
import com.cdb.model.dto.PageDto;
import com.cdb.model.mappers.DashboardDtoMapper;
import com.cdb.services.Impl.GestionOrdinateur;

@Controller
@RequestMapping("/dashboard.htm")
public class DashboardController {

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(DashboardController.class);
    
    @Autowired
    GestionOrdinateur gestionOrdinateur;
    
    public GestionOrdinateur getGestionOrdinateur( ){
        
        return gestionOrdinateur;
        
     }
    
    public DashboardController() {
        
        LOGGER.info("DashboardController instancié");
        
    }

    @RequestMapping(method=RequestMethod.GET)
    protected ModelAndView dashboardGet(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        LOGGER.info("DashboardController: GET");
        
        DashboardDto dashboard = DashboardDtoMapper
                .recuperationDashboardRequestGet(request);
        PageDto page = null;

        try {

            page = gestionOrdinateur.findOrdinateurByPage(dashboard.getNumPage(),
                            dashboard.getNbParPage(), dashboard.getFiltre(),
                            dashboard.getTrie(), dashboard.getDesc());

        } catch (ConnexionDatabaseException | RequeteQueryException e) {

            request.setAttribute("error", 1);
            
        }
        
        request.setAttribute("page", page);
        request.getSession().setAttribute("page", page);
        return new ModelAndView("dashboard");
        
    }
    
    @RequestMapping(method=RequestMethod.POST)
    protected ModelAndView dashboardPost(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        LOGGER.info("DashboardController: POST");
        
        try {

            gestionOrdinateur.suppressionOrdinateur(DashboardDtoMapper
                            .recuperationListSuppresionRequestPost(request));

        } catch (ConnexionDatabaseException | RequeteQueryException e) {

            request.setAttribute("error", 1);

        }

        return dashboardGet(request, response);
        
    }

}
