package com.cdb.views.controllers;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;
import com.cdb.model.dto.OrdinateurDto;
import com.cdb.services.Impl.GestionEntreprise;
import com.cdb.services.Impl.GestionOrdinateur;
import com.cdb.utils.Parse;
import com.cdb.utils.mappers.OrdinateurMapper;
import com.cdb.views.controllers.validation.OrdinateurDtoValidation;

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
    
    @Autowired
    OrdinateurDtoValidation ordinateurDtoValidation;
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        
        binder.setValidator(ordinateurDtoValidation);
        
    }
    
    public EditComputerController() {
        
        LOGGER.info("EditComputerController instancié");
        
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView editComputerGet(HttpServletRequest request, Model model) {
        
        LOGGER.info("EditComputerController: GET");
        recuperationModelAffichageEditComputer(request, model);
        model.addAttribute("ordinateurDto", new OrdinateurDto());
        return new ModelAndView("editComputer");
        
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView editComputerPost(@ModelAttribute("ordinateurDto") @Validated OrdinateurDto ordinateurDto,
            BindingResult result, HttpServletRequest request,Model model) {

        LOGGER.info("EditComputerController: POST");

        if (!result.hasErrors()) {

            try {

                gestionOrdinateur.createOrdinateur(
                        OrdinateurMapper.recuperationOrdinateur(ordinateurDto));

            } catch (RequeteQueryException | ConnexionDatabaseException e) {

                model.addAttribute("error", "L ordinateur n'a pas été modifié");
                return editComputerGet(request, model);

            }

            return new ModelAndView("redirect:/dashboard.htm");

        } else {
            
            recuperationModelAffichageEditComputer(request, model);
            return new ModelAndView("editComputer");

        }

    }
    
    private void recuperationModelAffichageEditComputer(HttpServletRequest request, Model model) {
        
        long id = Parse.parseLong(request.getParameter("ordinateur"), 0);

        try {

            OrdinateurDto ordinateur = gestionOrdinateur.findOrdinateurById(id);
            model.addAttribute("computer", ordinateur);
            model.addAttribute("companies",
                    gestionEntreprise.findEntreprise());

        } catch (ConnexionDatabaseException | EmptyResultDataAccessException | RequeteQueryException e) {

            model.addAttribute("error", "Erreur lors du chargement de l'ordinateur");

        }
        
    }
    
}
