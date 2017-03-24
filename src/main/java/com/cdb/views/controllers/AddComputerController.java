package com.cdb.views.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.cdb.utils.mappers.OrdinateurMapper;
import com.cdb.views.controllers.validation.OrdinateurDtoValidation;


@Controller
@RequestMapping("/addComputer.htm")
public class AddComputerController {
    
    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(AddComputerController.class);
    
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
    
    public AddComputerController() {
        
        LOGGER.info("AddComputerController instancié");
        
    }

    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView addComputerGet(Model model) {
        
        LOGGER.info("AddComputerController: GET");
        recuperationModelAffichageAddComputer(model);
        model.addAttribute("ordinateurDto", new OrdinateurDto());
        return new ModelAndView("addComputer");
        
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView addComputerPost(@ModelAttribute("ordinateurDto") @Validated OrdinateurDto ordinateurDto,
            BindingResult result, Model model) {

        LOGGER.info("AddComputerController: POST");

        if (!result.hasErrors()) {

            try {

                gestionOrdinateur.createOrdinateur(
                        OrdinateurMapper.recuperationOrdinateur(ordinateurDto));

            } catch (RequeteQueryException | ConnexionDatabaseException e) {

                model.addAttribute("error", "L ordinateur n'a pas été créer");
                return addComputerGet(model);

            }

            return new ModelAndView("redirect:/dashboard.htm");

        } else {
            
            recuperationModelAffichageAddComputer(model);
            return new ModelAndView("addComputer");

        }

    }
    
    private void recuperationModelAffichageAddComputer(Model model) {
        
        try {

            model.addAttribute("companies",
                    gestionEntreprise.findEntreprise());

        } catch (ConnexionDatabaseException | RequeteQueryException e) {

            model.addAttribute("error", "Erreur lors du chargement des noms d'entreprise");

        }
        
    }

}
