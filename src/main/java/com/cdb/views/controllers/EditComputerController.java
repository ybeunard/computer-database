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
import com.cdb.model.dto.OrdinateurDto;
import com.cdb.model.mappers.OrdinateurDtoMapper;
import com.cdb.model.mappers.OrdinateurMapper;
import com.cdb.services.Impl.GestionEntreprise;
import com.cdb.services.Impl.GestionOrdinateur;
import com.cdb.views.controllers.validation.Parse;
import com.cdb.views.controllers.validation.Validation;

@Controller
@RequestMapping("/editComputer.htm")
public class EditComputerController {

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(EditComputerController.class);
    
    @Autowired
    GestionOrdinateur gestionOrdinateur;
    
    public GestionOrdinateur getGestionOrdinateur( ){
        
        return gestionOrdinateur;
        
     }
    
    @Autowired
    GestionEntreprise gestionEntreprise;
    
    public GestionEntreprise getGestionEntreprise( ){
        
        return gestionEntreprise;
        
     }
    
    public EditComputerController() {
        
        LOGGER.info("EditComputerController instancié");
        
    }
    
    @RequestMapping(method=RequestMethod.GET)
    protected ModelAndView editComputerGet(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        LOGGER.info("EditComputerController: GET");
        long id = Parse.parseLong(request.getParameter("ordinateur"), 0);

        try {

            OrdinateurDto ordinateur = gestionOrdinateur.findOrdinateurById(id);

            if (ordinateur == null) {

                return new ModelAndView("dashboard");

            }

            request.setAttribute("computer", ordinateur);
            request.setAttribute("companies",
                    gestionEntreprise.findEntreprise());

        } catch (ConnexionDatabaseException | RequeteQueryException e) {

            request.setAttribute("error", 1);

        }
        
        return new ModelAndView("editComputer");
        
    }
    
    @RequestMapping(method=RequestMethod.POST)
    protected ModelAndView editComputerPost(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        LOGGER.info("EditComputerController: POST");
        OrdinateurDto ordinateur = OrdinateurDtoMapper
                .recuperationOrdinateurDto(request);

        if (Validation.validationOrdinateurDto(request, ordinateur)) {

            try {

                gestionOrdinateur.updateOrdinateur(
                        OrdinateurMapper.recuperationOrdinateur(ordinateur));

            } catch (RequeteQueryException | ConnexionDatabaseException e) {

                request.setAttribute("error", 1);
                return editComputerGet(request, response);

            }

            response.sendRedirect("dashboard.htm");
            return new ModelAndView("dashboard");

        } else {

            return editComputerGet(request, response);

        }
        
    }
    
}
